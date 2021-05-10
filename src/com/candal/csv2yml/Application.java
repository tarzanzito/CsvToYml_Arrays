package com.candal.csv2yml;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Application {

  private static int lineNumber;
  private static String line;
  private static String currentDir;

  public static void main(String[] args) {

    //Objective convert csv file into yml file with List <object> or map <(string, object) content.
    //Note: All methods are static because I do not understand anything about OOP !!!! :D ;P (So, simple and meets the objectives)
    
    try {

      System.out.println("Started....");

      currentDir = System.getProperty("user.dir");

      csvToYmlMap();
      csvToYmlList();

      System.out.println("Finished....");

    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }

  //////////////////////////////////////////////////////////////////////////////////////
  // this example:
  // input file:csv (line fields are separeted by ';')
  // Identificação Civil;http://interop.gov.pt/MDC/Cidadao/NIC;Cartão de Cidadão

  // Output file: yml
  // attributes:
  // __attribute-data-map:
  //
  // ____Cidadao-NomeCompleto:
  // ______name: Nome Completo
  // ______attr: http://interop.gov.pt/MDC/Cidadao/NomeCompleto
  // ______group: Cartão de Cidadão
  //
  // ____Cidadao-NIC:
  // ______name: Identificação Civil
  // ______attr: http://interop.gov.pt/MDC/Cidadao/NIC
  // ______group: Cartão de Cidadão

  // java
  //private Map<String, AttributeData> attributeDataMap = new HashMap<>();
  //public class AttributeData {
  //  private String name;
  //  private String attr;
  //  private String group;
  //}  
  //////////////////////////////////////////////////////////////////////////////////////
  public static void csvToYmlMap() throws Exception {
    
    lineNumber = 1;

    // output file
    OutputStreamWriter outputStreamWriter =  openTextFileForWrite(currentDir + "/src/resource/Out_Map.yml");
    
    // input file
    BufferedReader bufferedReader = openTextFileForRead(currentDir + "/src/resource/CSV_attributes.txt");

    //write parent tags
    outputStreamWriter.write(padLeftSpacesAndNewLine("attributes:", 0)); // root
    outputStreamWriter.write(padLeftSpacesAndNewLine("attribute-data-map:", 1)); // header

    line = bufferedReader.readLine();

    while (line != null) {

      String[] fields = line.split(";");

      if (fields.length != 3)
        throw new Exception("Spltit error, linenumber:" + lineNumber + "[" + line + "]");

      String key = fields[1].replaceAll("http://interop.gov.pt/MDC/", "").replaceAll("/", "-").trim();

      outputStreamWriter.write(padLeftSpacesAndNewLine(key + ":", 2)); // key
      outputStreamWriter.write(padLeftSpacesAndNewLine("name: " + fields[0], 3)); // name
      outputStreamWriter.write(padLeftSpacesAndNewLine("attr: " + fields[1], 3)); // attribute
      outputStreamWriter.write(padLeftSpacesAndNewLine("group: " + fields[2], 3)); // group
      outputStreamWriter.write("\n");

      outputStreamWriter.flush();

      line = bufferedReader.readLine();
      lineNumber++;

    }

    //close files
    outputStreamWriter.flush();
    outputStreamWriter.close();
    bufferedReader.close();
  }

  /////////////////////////////////////////////////////////////////////////////////////
  // this example:
  // input file:csv (line fields are separeted by ';')
  // Identificação Civil;http://interop.gov.pt/MDC/Cidadao/NIC;Cartão de Cidadão

  // Output file: yml
  // attributes:
  // __attribute-data-list:
  // ____-
  // ______name: Nome Completo
  // ______attr: http://interop.gov.pt/MDC/Cidadao/NomeCompleto
  // ______group: Cartão de Cidadão
  // ____-
  // ______name: Identificação Civil
  // ______attr: http://interop.gov.pt/MDC/Cidadao/NIC
  // ______group: Cartão de Cidadão

  // java
  //private List<AttributeId> attributeIdList = new ArrayList<>();
  //public class AttributeData {
  //  private String name;
  //  private String attr;
  //  private String group;
  //}
  //////////////////////////////////////////////////////////////////////////////////////
  public static void csvToYmlList() throws Exception {

    lineNumber = 1;

    // output file
    OutputStreamWriter outputStreamWriter =  openTextFileForWrite(currentDir + "/src/resource/Out_List.yml");
    
    // input file
    BufferedReader bufferedReader = openTextFileForRead(currentDir + "/src/resource/CSV_attributes.txt");

    //write parent tags
    outputStreamWriter.write(padLeftSpacesAndNewLine("attributes:", 0)); // root
    outputStreamWriter.write(padLeftSpacesAndNewLine("attribute-data-list:", 1)); // header

    line = bufferedReader.readLine();

    while (line != null) {

      String[] fields = line.split(";");

      if (fields.length != 3)
        throw new Exception("Spltit error, linenumber:" + lineNumber + "[" + line + "]");

      outputStreamWriter.write(padLeftSpacesAndNewLine("-", 2)); // init item
      outputStreamWriter.write(padLeftSpacesAndNewLine("name: " + fields[0], 3)); // name
      outputStreamWriter.write(padLeftSpacesAndNewLine("attr: " + fields[1], 3)); // attribute
      outputStreamWriter.write(padLeftSpacesAndNewLine("group: " + fields[2], 3)); // group
      //outputStreamWriter.write("\n");

      outputStreamWriter.flush();

      line = bufferedReader.readLine();
      lineNumber++;
    }

    //close files
    outputStreamWriter.flush();
    outputStreamWriter.close();
    bufferedReader.close();
  }

  private static String padLeftSpacesAndNewLine(String str, int level) {

    if (level == 0)
      return str + "\n";
    else {
      String format =  "%1$" + Integer.toString(str.length() + (level * 2)) + "s\n";
      return String.format(format, str);
    }
  }
  
  private static BufferedReader openTextFileForRead(String fullFileName) throws FileNotFoundException {
    
    FileInputStream fileInputStream = new FileInputStream(fullFileName);
    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    
    return bufferedReader;
  }
  
  private static OutputStreamWriter openTextFileForWrite(String fullFileName) throws FileNotFoundException {
  
    FileOutputStream fileOutputStream = new FileOutputStream(fullFileName);
    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);

    return outputStreamWriter;
  }
  
}
