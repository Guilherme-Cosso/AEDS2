import java.io.*;
import java.net.*;
class Html {
    static int maxtam = 25;
        /*
        *	@method : Confere se é o Fim
        *	@param : s String a ser analizada.
        *	@return : true or false
        */
        public static boolean ehFim(String s){
        
                boolean resp = true;
                if(s.length()==3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M')
                       resp = false;                 
                 return resp;
        } 
        
        /*
        *	@method : Acessa a url e
        *	@param : endereco String a ser analizada.
        *	@return : ulr        
        */

        public static String getHtml(String endereco){
                URL url;
                InputStream is = null;
                BufferedReader br;
                String resp = "", line;

                try {
                url = new URL(endereco);
                is = url.openStream();  // throws an IOException
                br = new BufferedReader(new InputStreamReader(is));
        
                while ((line = br.readLine()) != null) {
                resp += line + "\n";
                }
                } catch (MalformedURLException mue) {
                mue.printStackTrace();
                } catch (IOException ioe) {
                ioe.printStackTrace();
                } 
        
                try {
                is.close();
                } catch (IOException ioe) {
                // nothing to see here  
                }  
                return resp;
        }
        
        /*
        *	@method : conta
        *	@param : s String a ser analizada.
        *	@return : um vetor de int
        */

        public static int[] vetor(String s) 
        {

                int[] elemetos = new int[maxtam];
                for (int i = 0; i < maxtam; i++) {
                        elemetos[i] = 0;
                }
                for (int i = 0; i < s.length(); i++) {
                        if (s.charAt(i) == '\u003C') {
                        i++;
                                if (s.charAt(i) == 'b' && s.charAt(i + 1) == 'r' && s.charAt(i + 2) == '\u003E') {
                                        elemetos[23]++;
                                        i += 2;
                } else if (s.charAt(i) == 't' && s.charAt(i + 1) == 'a' && s.charAt(i+5) == '\u003E') {
                                        elemetos[24]++;
                                        i += 5;
                                        
                                }
                i--;
                        }else if (s.charAt(i) == 'a')
                                elemetos[0]++;
                        else if (s.charAt(i) == 'e')
                                elemetos[1]++;
                        else if (s.charAt(i) == 'i')
                                elemetos[2]++;
                        else if (s.charAt(i) == 'o')
                                elemetos[3]++;
                        else if (s.charAt(i) == 'u')
                                elemetos[4]++;
                        else if (s.charAt(i) == '\u00E1')
                                elemetos[5]++;
                        else if (s.charAt(i) == '\u00E9')
                                elemetos[6]++;
                        else if (s.charAt(i) == '\u00ED')
                                elemetos[7]++;
                        else if (s.charAt(i) == '\u00F3')
                                elemetos[8]++;
                        else if (s.charAt(i) == '\u00FA') // Â´
                                elemetos[9]++;
                        else if (s.charAt(i) == '\u00E0')
                                elemetos[10]++;
                        else if (s.charAt(i) == '\u00E8')
                                elemetos[11]++;
                        else if (s.charAt(i) == '\u00EC')
                                elemetos[12]++;
                        else if (s.charAt(i) == '\u00F2')
                                elemetos[13]++;
                        else if (s.charAt(i) == '\u00F9') // `
                                elemetos[14]++;
                        else if (s.charAt(i) == '\u00E3')
                                elemetos[15]++;
                        else if (s.charAt(i) == '\u00F5')// ~
                                elemetos[16]++;
                        else if (s.charAt(i) == '\u00E2')
                                elemetos[17]++;
                        else if (s.charAt(i) == '\u00EA')
                                elemetos[18]++;
                        else if (s.charAt(i) == '\u00EE')
                                elemetos[19]++;
                        else if (s.charAt(i) == '\u00F4')
                                elemetos[20]++;
                        else if (s.charAt(i) == '\u00FB')
                                elemetos[21]++;
                        else if (s.charAt(i) > 'a' && s.charAt(i) <= 'z')
                                elemetos[22]++;
                }
                return elemetos;
        }

        /*
        *	@method : Printa
        *	@param : s String a ser analizada.
        *       @param : num vetor de int a ser analizada.
        */
        
        public static void print(int[] num, String s){
                
        MyIO.println("a("+ num[0] + ") e("+ num[1] + ") i(" + num[2] + ") o("+ num[3] + ") u(" + num[4] +")"
                +" \u00E1(" + num[5] + ") \u00E9("+ num[6] + ") \u00ED(" + num[7] + ") \u00F3("+ num[8] + ") \u00FA(" + num[9] +")"+
                " \u00E0(" + num[10] + ") \u00E8("+ num[11] + ") \u00EC(" + num[12] + ") \u00F2("+ num[13] + ") \u00F9(" + num[14] +")"+
                " \u00E3(" + num[15] + ") \u00F5("+ num[16] + ") \u00E2(" + num[17] + ") \u00EA("+ num[18] + ") \u00EE(" + num[19] +")"+
                " \u00F4(" + num[20] + ") \u00FB("+ num[21] + ") consoante(" + num[22] + ") <br>("+ num[23] + ") <table>(" + num[24] +") " + s);
                }
        public static void main(String[] args) {
                MyIO.setCharset("utf-8");
                String  pag = "",
                        end = "";
                int[] numeros = new int[maxtam];    
                pag = MyIO.readLine();
                while(ehFim(pag)){
                end = MyIO.readLine();
                end = getHtml(end);
                numeros=vetor(end);
                print(numeros,pag);
                pag = MyIO.readLine();
                }
        }
}