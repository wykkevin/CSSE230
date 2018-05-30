import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Converts a text file of assembly code into machine code
 * Provides a file input then puts machine code in the console
 * Capable of performing psudeoinstructions to be documented in the design document
 * NOTE: We may not need a second $at register, so consider removing it in favor of more $t's or something.
 * @author Caleb Donoho
 */
public class Assembler {

	public static void main(String[] args) {
		//Swing components to choose file
		JFileChooser fileChooser = new JFileChooser();
		//Makes sure you can only select txt files(can be edited if we need other extensions)
		FileFilter filter = new FileNameExtensionFilter("txt file", new String[] {"txt"});
		fileChooser.setFileFilter(filter);
		fileChooser.addChoosableFileFilter(filter);
		//Makes it so it opens to the user's overall directory
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(new JFrame());
		if(result == JFileChooser.APPROVE_OPTION) {
			//Code must go inside here so that it runs once a text file has been chosen
			File selectedFile = fileChooser.getSelectedFile();
			try {
				String contents = new String(Files.readAllBytes(selectedFile.toPath()));
				String[] orig = contents.split("\n");
				ArrayList<String> lines = new ArrayList<String>();
				
				//First we must convert all the psuedoinstructions to lines of our code
				for(int i = 0; i < orig.length; i++) {
					String toAdd = convertPsuedoInstructions(orig[i]);
					String[] convertLines = toAdd.split("\n");
					for(String line : convertLines) {
						lines.add(line);
					}
				}	
				
				StringBuilder output = new StringBuilder();
				//HashMap contains all the code tags so that the psuedoinstruction jumps will be easy-peazy
				//Coding convention may be that your tag has to be on the line before the code block just because that is going to make things so much easier
				
				HashMap<String, Integer> codeTags = new HashMap<String, Integer>();
//				for(int i = 0; i < lines.size(); i++) {
//					if(lines.get(i).length() != 0) {
//						String test = lines.get(i).trim().split(" ")[0];
//						if(test.charAt(test.length()-1) == ':') {
//							codeTags.put(test.substring(0, test.length() - 1), i + 4 - codeTags.size());
//							lines.remove(i);
//							System.out.println(test);
//							System.out.println(i+4-codeTags.size());
//						}
//					}
//				}
				int index = 0;
				while(index < lines.size()) {
					if(lines.get(index).length() != 0) {
						String test = lines.get(index).trim().split(" ")[0];
						if(test.charAt(test.length()-1) == ':') {
							codeTags.put(test.substring(0, test.length() - 1), index + 4);
							lines.remove(index);
							System.out.println(test);
							System.out.println(index+4);
						}else{
							index++;
						}
					}
				}
				
				if(lines.get(lines.size()-1).equals("")){
					lines.remove(lines.size()-1);
				}
				
				//Add code to set up $sp
				String totalLines = Integer.toBinaryString(lines.size() + 5);//4 is the current number of startup code lines + 1 to separate the instructions from the stack
				while(totalLines.length() < 16) {
					totalLines = "0" + totalLines;
				}
				lines.add(0, "lui b" + totalLines.substring(0, 8));
				lines.add(1, "ori b" + totalLines.substring(8));
				lines.add(2, "sacc $sp");
				lines.add(3, "lacc $0");
				
				//Should replace all tags
				for(int i = 0; i < lines.size(); i++) {
					for(String key : codeTags.keySet()) {
						String immBinary = Integer.toBinaryString(codeTags.get(key));
						while(immBinary.length() < 16) {
							immBinary = "0" + immBinary;
						}
						String line = lines.get(i).trim(); 
						if(line.startsWith("lui") && line.contains(key)) {
							line = line.replace(key, "b" + immBinary.substring(0, 8));
							lines.remove(i);
							lines.add(i, line);
						}
						else if(line.startsWith("ori") && line.contains(key)) {
							line = line.replace(key, "b" + immBinary.subSequence(8, 16));
							lines.remove(i);
							lines.add(i, line);
						}
					}
				}				

				System.out.println(lines.toString());
				
				//Translate all the lines
				for(int i = 0; i < lines.size(); i++) {
					try {
						//Hex Code
//						String hex = Integer.toHexString(Integer.parseInt(translateLine(lines.get(i).trim(), i, codeTags),2));
//						while(hex.length() < 4) {
//							hex = "0" + hex;
//						}
//						output.append(hex);
						//Binary Code
						output.append(translateLine(lines.get(i).trim(), i, codeTags));
						output.append("\n");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println(output.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static String convertPsuedoInstructions(String line) {
		String trimmed = line.trim();
		if(trimmed.length() == 0 || trimmed.charAt(0) == '#') {
			return "";
		}
		String op = trimmed.split(" ")[0].trim();
		switch(op) {
			case "li":
			case "addp":
			case "subp":
			case "sltp":
			case "move":
			case "addi":
			case "andi":
			case "j":
			case "jalp":
			case "beqp":
			case "bnep":
			case "blt":
			case "bgt":
			case "ble":
			case "bge":
				return translatePsuedoInstruction(line);
			default:
				return line;
		}
	}
	
	/**
	 * Translates a single line from assembly to machine code
	 * @param line String of the given line
	 * @param lineNumber only used for more descriptive error messages
	 * @param codeTags Contains all the jump tags we parsed through to start
	 * @return String of translated line(ones and zeros)
	 * @throws Exception If at any point an input is invalid, say so and say the line its on.
	 */
	private static String translateLine(String line, int lineNumber, HashMap<String,Integer> codeTags) throws Exception{
		String trimmed = line.trim();
		if(trimmed.charAt(0) == '#' || trimmed.length() == 0) {
			return "";
		}
		String op = trimmed.split(" ")[0].trim();
		switch(op) {
			case "add":
			case "sub":
			case "lmem":
			case "smem":
			case "slt":
			case "or":
			case "and":
			case "jr":
			case "jal":
			case "lacc":
			case "sacc":
				return translateTypeOne(line, lineNumber);
			case "sll":
			case "lui":
			case "ori":
				return translateTypeTwo(line, lineNumber);
			case "beq":
			case "bne":
				return translateTypeThree(line, lineNumber);
			default:
				return "";
		}
	}
	
	/**
	 * Translates any of the psuedoinstructions. May split out each if block content into its own method to just make it slightly more clean
	 * @param line Psuedoinstruction line
	 * @param lineNumber Line # of psuedoinstruction. Used as the line number for all resulting lines for debugging
	 * @param codeTags All the tags in a nice HashMap
	 * @return All resulting lines of machine code from the psuedoinstruction
	 * @throws Exception
	 */
	private static String translatePsuedoInstruction(String line){
		String[] instruction = line.split(" ");
		String op = instruction[0].trim();
		if(op.equals("li")) {
			//Converts li to a lui and an ori
			String des = instruction[1].trim();
			int imm = Integer.parseInt(instruction[2].trim());
			String immBinary = Integer.toBinaryString(imm);
			while(immBinary.length() < 16) {
				immBinary = "0" + immBinary;
			}
			//Fix this to pass base ten instead of base 2
			String line1 = "sacc $at0";
			String line2 = "lui b" + immBinary.substring(0, 8);
			String line3 = "ori b" + immBinary.substring(8, 16);
			String line4 = "sacc " + des;
			if(des.equals("$acc")) {
				return line1 + "\n" + line2 + "\n" + line3 + "\n" + line4;
			}
			String line5 = "lacc $at0";
			return line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n" + line5;		
		}
		if(op.equals("addp") || op.equals("subp") || op.equals("sltp")) {
			//Moves what is in the accumulator out, does the operation, and puts it back
			String des = instruction[1].trim();
			String reg1 = instruction[2].trim();
			String reg2 = instruction[3].trim();
			if(reg1.equals("$acc")) {
				reg1 = "at0";
			}
			if(reg2.equals("$acc")) {
				reg2 = "$at0";
			}
			String line1 = "sacc $at0";
			String line2 = "lacc " + reg1;
			String line3;
			if(op.equals("addp")) {
				line3 = "add " + reg2;
			}
			else if(op.equals("subp")){
				line3 = "sub " + reg2;
			}
			else {
				line3 = "slt " + reg2;
			}				
			String line4 = "sacc " + des;
			if(des.equals("$acc")) {
				return line1 + "\n" + line2 + "\n"
						+ line3 + "\n" + line4;
			}
			String line5 = "lacc $at0";
			return line1 + "\n" + line2 + "\n"
					+ line3 + "\n" + line4 + "\n"
					+ line5;
		}
		if(op.equals("move")) {
			//Moves the contents of a register to another register without changing the value in the accumulator
			String des = instruction[1].trim();
			String src = instruction[2].trim();
			String line1 = "sacc $at0";
			String line2 = "lacc " + src;
			String line3 = "sacc " + des;
			String line4 = "lacc $at0";
			return line1 + "\n" + line2 + "\n"
					+ line3 + "\n" + line4;
		}
		if(op.equals("addi") || op.equals("andi")) {
			//Moves what is in the accumulator out, does the operation, and puts it back
			String des = instruction[1].trim();
			String src = instruction[2].trim();
			if(src.equals("$acc")) {
				src = "$at0";
			}
			String imm = instruction[3].trim();
			String immBinary = Integer.toBinaryString(Integer.parseInt(imm));
			while(immBinary.length() < 16) {
				immBinary = "0" + immBinary;
			}
			String line1 = "sacc $at0";
			String line2 = "lui b" + immBinary.substring(0, 8);
			String line3 = "ori b" + immBinary.substring(8, 16);
			String line4;
			if(op.equals("addi")) {
				line4 = "add " + src;
			}
			else {
				line4 = "and " + src;
			}
			String line5 = "sacc " + des;
			if(des.equals("$acc")) {
				return line1 + "\n" + line2 + "\n"
						+ line3 + "\n" + line4 + "\n"
						+ line5 + "\n" + line5;
			}
			String line6 = "lacc $at0";
			return line1 + "\n" + line2 + "\n"
					+ line3 + "\n" + line4 + "\n"
					+ line5 + "\n"
					+ line6;
		}
		if(op.equals("j") || op.equals("jalp")) {
			String tag = instruction[1].trim();
			String line1 = "sacc $at0";
			String line2 = "lui " + tag;
			String line3 = "ori " + tag;
			String line4 = "sacc $at1";
			String line5 = "lacc $at0";
			String line6;
			if(op.equals("j")) {
				line6 = "jr $at1";
			}
			else {
				line6 = "jal $at1";
			}
			return line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n" + line5 + "\n" + line6;
		}
		//TODO If it performs the branch, it won't keep what was in the accumulator. TODOed this to make sure that people coding know this
		if(op.equals("beqp") || op.equals("bnep")) {
			String reg1 = instruction[1].trim();
			String reg2 = instruction[2].trim();
			if(reg1.equals("$acc")) {
				reg1 = "at0";
			}
			if(reg2.equals("$acc")) {
				reg2 = "$at0";
			}
			String tag = instruction[3].trim();
			String line1 = "sacc $at0";
			String line2 = "lui " + tag;
			String line3 = "ori " + tag;
			String line4 = "sacc $at1";	
			String line5 = "lacc " + reg1;
			String line6;
			if(op.equals("beqp")) {
				line6 = "beq " + reg2 + " $at1";
			}
			else {
				line6 = "bne " + reg2 + " $at1";
			}
			String line7 = "lacc $at0";
			return line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n" + line5 + "\n" + line6 + "\n" + line7;
		}
		if(op.equals("blt") || op.equals("bgt") || op.equals("ble") || op.equals("bge")) {
			String reg1 = instruction[1].trim();
			String reg2 = instruction[2].trim();
			if(reg1.equals("$acc")) {
				reg1 = "at0";
			}
			if(reg2.equals("$acc")) {
				reg2 = "$at0";
			}
			String tag = instruction[3].trim();
			String line1 = "sacc $at0";
			String line2 = "lui " + tag;
			String line3 = "ori " + tag;
			String line4 = "sacc $at1";
			String line5;
			String line6;
			String line7;
			if(op.equals("blt")) {
				line5 = "lacc " + reg1;
				line6 = "slt " + reg2;
				line7 = "bne $0 $at1";
			}
			else if(op.equals("bge")) {
				line5 = "lacc " + reg1;
				line6 = "slt " + reg2;
				line7 = "beq $0 $at1";
			}
			else if(op.equals("bgt")) {
				line5 = "lacc " + reg2;
				line6 = "slt " + reg1;
				line7 = "bne $0 $at1";
			}
			else {
				line5 = "lacc " + reg2;
				line6 = "slt " + reg1;
				line7 = "beq $0 $at1";
			}
			String line8 = "lacc $at0";
			return line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n" + line5 + "\n" + line6 + "\n" + line7 + "\n" + line8;
		}
		return "";
	}
	
	/**
	 * Translates a type one instruction
	 * @param line
	 * @param lineNumber
	 * @return machine code representation
	 * @throws Exception
	 */
	private static String translateTypeOne(String line, int lineNumber) throws Exception{
		String[] instruction = line.split(" ");
		String op = instruction[0].trim();
		String reg = instruction[1].trim();		
		String imm = (instruction.length == 3 && instruction[2].charAt(0) != '#')
			? instruction[2].trim() 
			: "b00000000";
		StringBuilder toReturn = new StringBuilder();
		toReturn.append(convertOpCode(op, lineNumber));
		toReturn.append(convertRegister(reg, lineNumber));
		
		String immBinary;
		if(imm.startsWith("b")) {
			immBinary = imm.substring(1);
		}
		else {
			immBinary = Integer.toBinaryString(Integer.parseInt(imm));
		}
		if(immBinary.length() > 8) {
			immBinary = immBinary.substring(immBinary.length()-8, immBinary.length());
		}
		if(op.equals("lmem") || op.equals("smem")) {
			while(immBinary.length() < 8) {
				immBinary = immBinary.charAt(0) + immBinary;
			}
		}
		else {
			while(immBinary.length() < 8) {
				immBinary = "0" + immBinary;
			}
		}
		toReturn.append(immBinary);
		
		return toReturn.toString();
	}
	
	/**
	 * Translates a type two instruction
	 * @param line
	 * @param lineNumber
	 * @return machine code representation
	 * @throws Exception
	 */
	private static String translateTypeTwo(String line, int lineNumber) throws Exception{
		String[] instruction = line.split(" ");
		String op = instruction[0].trim();
		String imm = instruction[1].trim();
		String unused = (instruction.length == 3 && instruction[2].charAt(0) != '#')
				? instruction[2].trim()
				: "b0000";
		StringBuilder toReturn = new StringBuilder();

		toReturn.append(convertOpCode(op, lineNumber));
		
		String unusedBinary;
		if(unused.startsWith("b")) {
			unusedBinary = unused.substring(1);
		}
		else {
			unusedBinary = Integer.toBinaryString(Integer.parseInt(unused));
		}
		toReturn.append(unusedBinary);
		
		String immBinary;
		if(imm.startsWith("b")) {
			immBinary = imm.substring(1);
		}
		else {
			immBinary = Integer.toBinaryString(Integer.parseInt(imm));
		}
		while(immBinary.length() < 8) {
			immBinary = "0" + immBinary;
		}
		toReturn.append(immBinary);
		
		return toReturn.toString();
	}
	
	/**
	 * Translates a type three instruction
	 * @param line
	 * @param lineNumber
	 * @return machine code representation
	 * @throws Exception
	 */
	private static String translateTypeThree(String line, int lineNumber) throws Exception {
		String[] instruction = line.split(" ");
		String op = instruction[0].trim();
		String reg = instruction[1].trim();
		String des = instruction[2].trim();
		String unused = (instruction.length == 4 && instruction[3].charAt(0) != '#')
				? instruction[3].trim()
				: "b0000";
		StringBuilder toReturn = new StringBuilder();
		toReturn.append(convertOpCode(op, lineNumber));
		toReturn.append(convertRegister(reg, lineNumber));
		toReturn.append(convertRegister(des, lineNumber));
		
		String unusedBinary;
		if(unused.startsWith("b")) {
			unusedBinary = unused.substring(1);
		}
		else {
			unusedBinary = Integer.toBinaryString(Integer.parseInt(unused));
		}
		while(unusedBinary.length() < 4) {
			unusedBinary = "0" + unusedBinary;
		}
		toReturn.append(unusedBinary);
		
		return toReturn.toString();
	}
	
	/**
	 * Converts a given op string to the corresponding binary representation
	 * @param op
	 * @param lineNumber
	 * @return Binary representation of the opcode
	 * @throws Exception if opcode doesn't exist
	 */
	private static String convertOpCode(String op, int lineNumber) throws Exception {
		switch(op){
			case "add":
				return "0000";
			case "sub":
				return "0001";
			case "lmem":
				return "0010";
			case "smem":
				return "0011";
			case "beq":
				return "0100";
			case "bne":
				return "0101";
			case "sll":
				return "0110";
			case "slt":
				return "0111";
			case "or":
				return "1000";
			case "and":
				return "1001";
			case "lui":
				return "1010";
			case "jr":
				return "1011";
			case "jal":
				return "1100";
			case "ori":
				return "1101";
			case "lacc":
				return "1110";
			case "sacc":
				return "1111";
			default:
				throw new Exception("Invalid opcode line: " + lineNumber);
		}
	}
	
	/**
	 * Converts a register string to the corresponding binary
	 * @param reg 
	 * @param lineNumber
	 * @return binary translation
	 * @throws Exception if reg is invalid
	 */
	private static String convertRegister(String reg, int lineNumber) throws Exception{
		switch(reg){
			case "$0":
				return "0000";
			case "$acc":
				return "0001";
			case "$a0":
				return "0010";
			case "$a1":
				return "0011";
			case "$a2":
				return "0100";
			case "$a3":
				return "0101";
			case "$v0":
				return "0110";
			case "$v1":
				return "0111";
			case "$t0":
				return "1000";
			case "$t1":
				return "1001";
			case "$s0":
				return "1010";
			case "$s1":
				return "1011";
			case "$ra":
				return "1100";
			case "$sp":
				return "1101";
			case "$at0":
				return "1110";
			case "$at1":
				return "1111";
			default:
				throw new Exception("Invalid register in line: " + lineNumber + " " + reg);	
		}
	}
}
