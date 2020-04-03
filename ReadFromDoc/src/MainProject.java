import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class MainProject {

	public static void main(String[] args) throws IOException, InterruptedException {
	
		// 1  open new doc file and define variables
		// 1.1
		final int SIZE =12;
		String toPath ,toDocName , fromPath ,fromDocName ,textFromDoc , wordToTranslate , textToDoc="" , result ;

		Google google =new Google();

		
		// 1.2
		toPath="/Users/nassarmahmood/Downloads/";
		toDocName="nsdfgsd.doc";
		fromPath="/Users/nassarmahmood/Downloads/";
		fromDocName="newc.doc";
		
		XWPFDocument document = new XWPFDocument();

		// 2 Write the Document in file system
		FileOutputStream out = new FileOutputStream (toPath+toDocName);

		// 3 read from doc
		FileInputStream fis=new FileInputStream(fromPath+fromDocName);
		HWPFDocument doc = new HWPFDocument(fis);
		textFromDoc =doc.getDocumentText();
		
		// 4 loop to get the chars from the doc 
		for (int i = 0 ; i < textFromDoc.length();i++) {
			
			//4.1  check if the char equal to '<' that mean will start word to translate after the '<'
			if (textFromDoc.charAt(i)=='<') {
				wordToTranslate="";
				i++;
				
				while(textFromDoc.charAt(i)!='>') {
					wordToTranslate+=textFromDoc.charAt(i);
					i++;
				}
				result=google.Translate(wordToTranslate);
				result=removeNewLine(result);
				textToDoc+=wordToTranslate+" ("+result+")";
				
				i++;
			}

			//5 write to the string 
			
			textToDoc+=textFromDoc.charAt(i);
			

		

		}// end loop 1 (for)
		
		// close google page
		google.closePage();
		
		
		
		
		XWPFParagraph para =document.createParagraph();
		para.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun paraRun = para.createRun();
		paraRun.setFontSize(SIZE);
		paraRun.setText(textToDoc);
		document.write(out);
		out.close(); 

	}
	public static String removeNewLine(String str) {
	    if (str.charAt(str.length()-2)=='\n'){
	        str = str.replace(str.substring(str.length()-2), "");
	        return str;
	    } else{
	        return str;
	    }
	}
}

	






