public class Main {
    public static void main(String[] args) {
        String inputCsvfile = "C:/Users/rajni/Desktop/input/test1.csv";
        String outputCsvfile = "C:/Users/rajni/Desktop/output/test1.csv";
        CSVReader.read(inputCsvfile);
//        CSVFileCopier.copyPaste(inputCsvfile,outputCsvfile);
        CSVWriter.write(outputCsvfile,"9541310605","Rajni Sharma");
//        APIRequest.APIRequest();
//        APIRequestHttps.APIRequest();
//        try{
//            System.out.println(EncryptDecrypt.aes128Encrypt("haha"));
//            System.out.println(EncryptDecrypt.aes128Decrypt("jhI5nAdyb1qOEjmcB3JvWlc2chhVTFzy8+PKHE/A0Js="));
//
//        }catch(Exception e){
//            return ;
//        }
    }
}