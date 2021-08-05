import java.io.*;
import java.net.*;

class Html2 {
        static int maxtam = 25;

        public static boolean ehFim(String s) {
                boolean resp = true;
                if (s.length() == 1 && s.charAt(0) == 'F' && s.charAt(0) == 'I' && s.charAt(0) == 'M')
                        resp = false;

                return resp;
        }

        public static String getHtml(String endereco) {
                URL url;
                InputStream is = null;
                BufferedReader br;
                String resp = "", line;

                try {
                        url = new URL(endereco);
                        is = url.openStream();
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
                }
                return resp;
        }

        public static int[] vetor(String s) {

                int[] elemetos = new int[maxtam];
                for (int i = 0; i < maxtam; i++) {
                        elemetos[i] = 0;
			//System.out.println("elemento " + i + "  " + elemetos[i]);
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
                        else if (s.charAt(i) == '\u00FA') // ´
                                elemetos[9]++;
                        else if (s.charAt(i) == '\u00E0')
                                elemetos[10]++;
                        else if (s.charAt(i) == '\u00EC')
                                elemetos[11]++;
                        else if (s.charAt(i) == '\u00E8')
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

        public static void print(int[] num, String s) {
                MyIO.println("a(" + num[0] + ") e(" + num[1] + ") i(" + num[2] + ") o(" + num[3] + ") u(" + num[4] + ")"
                                + " á(" + num[5] + ") é(" + num[6] + ") í(" + num[7] + ") ó(" + num[8] + ") ú(" + num[9]
                                + ")" + " à(" + num[10] + ") è(" + num[11] + ") ì(" + num[12] + ") ò(" + num[13]
                                + ") ù(" + num[14] + ")" + " ã(" + num[15] + ") õ(" + num[16] + ") â(" + num[17]
                                + ") ê(" + num[18] + ") î(" + num[19] + ")" + " ô(" + num[20] + ") \u00FB(" + num[21]
                                + ") consoante(" + num[22] + ") <br>(" + num[23] + ") <table>(" + num[24] + ") " + s);
        }

        public static void main(String[] args) {
                MyIO.setCharset("utf-8");
                String pag = "", end;
                int[] numeros = new int[maxtam];
                pag = MyIO.readLine();
                while (ehFim(pag)) {
                        end = MyIO.readLine();
                        end = getHtml(end);
                        numeros = vetor(end);
                        print(numeros, pag);
                        pag = MyIO.readLine();

                }
        }
}
