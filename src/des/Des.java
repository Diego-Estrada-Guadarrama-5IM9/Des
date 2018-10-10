//ESTRADA GUADARRAMA DIEGO
//5IM9
package Des;

public class Des  {
  String C[] = new String [17] ;
  String Ki[] = new String [17];
  String Kf[] = new String [17];
  String D[] = new String [17];
  String R[] = new String [17];
  String L[] = new String [17];
   String PI;
   String PF;
   
  //Método que realiza el DES
   public void Des(String llave, String dato){
       
       String RXor;
       String E;
       String Sf;
       String P;
       
       
       Rondas(llave);
       Llaves();
       PI = PermutacionInicial(dato);
       L[0] = PI.substring(0, 32);
       R[0] = PI.substring(32,64);
       E = Expansion(R[0]);
       RXor = Xor(E, Kf[1]);
       Sf = Sss(RXor);
       P= Permutacion(Sf);
       RXor = Xor(P, L[0]);
       R[1] = RXor;
       
       for (int i = 1; i < 16; i++) {
            L[i] = R[i-1];
            E = Expansion(R[i]);
            RXor = Xor(E, Kf[i+1]);
            Sf = Sss(RXor);
            P= Permutacion(Sf);
            RXor = Xor(P, L[i]);
            R[i+1] = RXor;
            System.out.println("R"+(i+1)+"= "+R[i+1]);
       }
       L[16] = R[15];
       String Fin = R[16] + L[16]; 
       PF = PermutacionFinal(Fin);
       System.out.println("CIFRADO FINAL:");
       System.out.println(PF);
   }
   
   //Método que hace un Xor entre dos palabras
   public String Xor(String P1, String P2){
       String RXor = "";
        char RPrueba[] = new char [P1.length()];
        
       for (int i = 0; i < P1.length(); i++) {
           if(P1.charAt(i) =='0' && P2.charAt(i) == '0'){
               RPrueba[i] = '0';
           }
           else if(P1.charAt(i) =='1' && P2.charAt(i) == '1'){
               RPrueba[i] = '0';
           } 
           else if (P1.charAt(i) =='0' && P2.charAt(i) == '1'){
               RPrueba[i] = '1';
           }
           else if (P1.charAt(i) =='1' && P2.charAt(i) == '0'){
               RPrueba[i] = '1';
           }
           RXor += Character.toString(RPrueba[i]);
          // System.out.print(RXor);
       }
       return RXor;
   }
   
   //Método que hace los desplazamientos de las rondas
   public void Rondas(String llave){
               String C0 = Character.toString(llave.charAt(56)) + Character.toString(llave.charAt(48)) +
        Character.toString(llave.charAt(40)) + Character.toString(llave.charAt(32)) +
        Character.toString(llave.charAt(24)) + Character.toString(llave.charAt(16)) +
        Character.toString(llave.charAt(8)) + Character.toString(llave.charAt(0)) +    
        Character.toString(llave.charAt(57)) + Character.toString(llave.charAt(49)) +
        Character.toString(llave.charAt(41)) + Character.toString(llave.charAt(33)) +
        Character.toString(llave.charAt(25)) + Character.toString(llave.charAt(17)) +
        Character.toString(llave.charAt(9)) + Character.toString(llave.charAt(1)) +
        Character.toString(llave.charAt(58)) + Character.toString(llave.charAt(50)) +
        Character.toString(llave.charAt(42)) + Character.toString(llave.charAt(34)) +
        Character.toString(llave.charAt(26)) + Character.toString(llave.charAt(18)) +
        Character.toString(llave.charAt(10)) + Character.toString(llave.charAt(2)) +           
        Character.toString(llave.charAt(59)) + Character.toString(llave.charAt(51)) +
        Character.toString(llave.charAt(43)) + Character.toString(llave.charAt(35));
		
        //inicializamos D0
        String D0 = Character.toString(llave.charAt(62)) + Character.toString(llave.charAt(54)) +
        Character.toString(llave.charAt(46)) + Character.toString(llave.charAt(38)) +
        Character.toString(llave.charAt(30)) + Character.toString(llave.charAt(22)) +
        Character.toString(llave.charAt(14)) + Character.toString(llave.charAt(6)) +
        Character.toString(llave.charAt(61)) + Character.toString(llave.charAt(53)) +
        Character.toString(llave.charAt(45)) + Character.toString(llave.charAt(37)) +
        Character.toString(llave.charAt(29)) + Character.toString(llave.charAt(21)) +
        Character.toString(llave.charAt(13)) + Character.toString(llave.charAt(5)) +
        Character.toString(llave.charAt(60)) + Character.toString(llave.charAt(52)) +
        Character.toString(llave.charAt(44)) + Character.toString(llave.charAt(36)) +
        Character.toString(llave.charAt(28)) + Character.toString(llave.charAt(20)) +
        Character.toString(llave.charAt(12)) + Character.toString(llave.charAt(4)) +
        Character.toString(llave.charAt(27)) + Character.toString(llave.charAt(19)) +
        Character.toString(llave.charAt(11)) + Character.toString(llave.charAt(3));
        
		//unimos C0 y D0 para dar la permutacion inicial
        String PI = C0 + D0;
        System.out.println("Permutacion Inicial = "+PI);
		
        //igualamos la C[0] y D[0] a sus respectivas claves ya antes creadas
        C[0] = C0;
        D[0] = D0;
        //System.out.println("C0= "+C[0] + "  D0= " +D[0]);
		 
		// se inicia el for que recorrera las llaves
        for (int i = 1; i <= 16; i++) {
			//en las rondas en que solo se recorre 1 vez 
            if(i == 1 || i == 2 || i == 9 || i == 16){
                    C[i] = C[i-1].substring(1) + Character.toString(C[i-1].charAt(0));
                    D[i] = D[i-1].substring(1) + Character.toString(D[i-1].charAt(0));
            }
			//en las demas rondas
            else{
                C[i] = C[i-1].substring(2) + Character.toString(C[i-1].charAt(0)) + Character.toString(C[i-1].charAt(1));         
                D[i] = D[i-1].substring(2) + Character.toString(D[i-1].charAt(0)) + Character.toString(D[i-1].charAt(1));        
            }
          // System.out.println("C"+i+"=" +C[i]+" D"+i+"= "+D[i]);
       }
   }
   
   //Método que nos crea las llaves (las K)
   public void Llaves(){
               for (int i = 1; i <= 16; i++) {
			//ki es la union de la C y la D, mientras que kf será la permutacion de la Ki
            Ki[i] = C[i] + D[i];
            Kf[i] = Character.toString(Ki[i].charAt(13)) + Character.toString(Ki[i].charAt(16)) +
                    Character.toString(Ki[i].charAt(10)) + Character.toString(Ki[i].charAt(23)) +
                    Character.toString(Ki[i].charAt(0)) + Character.toString(Ki[i].charAt(4)) +
                    Character.toString(Ki[i].charAt(2)) + Character.toString(Ki[i].charAt(27)) +
                    
                    Character.toString(Ki[i].charAt(14)) + Character.toString(Ki[i].charAt(5)) +
                    Character.toString(Ki[i].charAt(20)) + Character.toString(Ki[i].charAt(9)) +
                    Character.toString(Ki[i].charAt(22)) + Character.toString(Ki[i].charAt(18)) +
                    Character.toString(Ki[i].charAt(11)) + Character.toString(Ki[i].charAt(3)) +
                    
                    Character.toString(Ki[i].charAt(25)) + Character.toString(Ki[i].charAt(7)) +
                    Character.toString(Ki[i].charAt(15)) + Character.toString(Ki[i].charAt(6)) +
                    Character.toString(Ki[i].charAt(26)) + Character.toString(Ki[i].charAt(19)) +
                    Character.toString(Ki[i].charAt(12)) + Character.toString(Ki[i].charAt(1)) +
                    
                    Character.toString(Ki[i].charAt(40)) + Character.toString(Ki[i].charAt(51)) +
                    Character.toString(Ki[i].charAt(30)) + Character.toString(Ki[i].charAt(36)) +
                    Character.toString(Ki[i].charAt(46)) + Character.toString(Ki[i].charAt(54)) +
                    Character.toString(Ki[i].charAt(29)) + Character.toString(Ki[i].charAt(39)) +
                    
                    Character.toString(Ki[i].charAt(50)) + Character.toString(Ki[i].charAt(44)) +
                    Character.toString(Ki[i].charAt(32)) + Character.toString(Ki[i].charAt(47)) +
                    Character.toString(Ki[i].charAt(43)) + Character.toString(Ki[i].charAt(48)) +
                    Character.toString(Ki[i].charAt(38)) + Character.toString(Ki[i].charAt(55)) +
                    
                    Character.toString(Ki[i].charAt(33)) + Character.toString(Ki[i].charAt(52)) +
                    Character.toString(Ki[i].charAt(45)) + Character.toString(Ki[i].charAt(41)) +
                    Character.toString(Ki[i].charAt(49)) + Character.toString(Ki[i].charAt(35)) +
                    Character.toString(Ki[i].charAt(28)) + Character.toString(Ki[i].charAt(31));
           // System.out.println("K"+i+"= "+Kf[i]);

       }
   }
   
   //Método que realiza la expansión correspondiente
   public String Expansion(String exp){
           exp = Character.toString(exp.charAt(31)) + Character.toString(exp.charAt(0)) +
                Character.toString(exp.charAt(1)) + Character.toString(exp.charAt(2)) +
                Character.toString(exp.charAt(3)) + Character.toString(exp.charAt(4)) +
                Character.toString(exp.charAt(3)) + Character.toString(exp.charAt(4)) +
                   
                Character.toString(exp.charAt(5)) + Character.toString(exp.charAt(6)) +
                Character.toString(exp.charAt(7)) + Character.toString(exp.charAt(8)) +
                Character.toString(exp.charAt(7)) + Character.toString(exp.charAt(8)) +
                Character.toString(exp.charAt(9)) + Character.toString(exp.charAt(10)) +
                   
                Character.toString(exp.charAt(11)) + Character.toString(exp.charAt(12)) +
                Character.toString(exp.charAt(11)) + Character.toString(exp.charAt(12)) +
                Character.toString(exp.charAt(13)) + Character.toString(exp.charAt(14)) +
                Character.toString(exp.charAt(15)) + Character.toString(exp.charAt(16)) +
                   
                 Character.toString(exp.charAt(15)) + Character.toString(exp.charAt(16)) +
                 Character.toString(exp.charAt(17)) + Character.toString(exp.charAt(18)) +
                 Character.toString(exp.charAt(19)) + Character.toString(exp.charAt(20)) +
                 Character.toString(exp.charAt(19)) + Character.toString(exp.charAt(20)) +
                   
                 Character.toString(exp.charAt(21)) + Character.toString(exp.charAt(22)) +
                 Character.toString(exp.charAt(23)) + Character.toString(exp.charAt(24)) +
                 Character.toString(exp.charAt(23)) + Character.toString(exp.charAt(24)) +
                 Character.toString(exp.charAt(25)) + Character.toString(exp.charAt(26)) +
                   
                 Character.toString(exp.charAt(27)) + Character.toString(exp.charAt(28)) +
                 Character.toString(exp.charAt(27)) + Character.toString(exp.charAt(28)) +
                 Character.toString(exp.charAt(29)) + Character.toString(exp.charAt(30)) +
                 Character.toString(exp.charAt(31)) + Character.toString(exp.charAt(0));
           return exp;
   }
   
   //Método que hace la permutación correspondiente
   public String Permutacion(String per){
       per = Character.toString(per.charAt(15)) + Character.toString(per.charAt(6)) +
             Character.toString(per.charAt(19)) + Character.toString(per.charAt(20)) +
             Character.toString(per.charAt(28)) + Character.toString(per.charAt(11)) +
             Character.toString(per.charAt(27)) + Character.toString(per.charAt(16)) +
               
             Character.toString(per.charAt(0)) + Character.toString(per.charAt(14)) +
             Character.toString(per.charAt(22)) + Character.toString(per.charAt(25)) +
             Character.toString(per.charAt(4)) + Character.toString(per.charAt(17)) +
             Character.toString(per.charAt(30)) + Character.toString(per.charAt(9)) +
               
             Character.toString(per.charAt(1)) + Character.toString(per.charAt(7)) +
             Character.toString(per.charAt(23)) + Character.toString(per.charAt(13)) +
             Character.toString(per.charAt(31)) + Character.toString(per.charAt(26)) +
             Character.toString(per.charAt(2)) + Character.toString(per.charAt(8)) +
               
             Character.toString(per.charAt(18)) + Character.toString(per.charAt(12)) +
             Character.toString(per.charAt(29)) + Character.toString(per.charAt(5)) +
             Character.toString(per.charAt(21)) + Character.toString(per.charAt(10)) +
             Character.toString(per.charAt(3)) + Character.toString(per.charAt(24));
       return per;
   }
   
   //Método que crea el funcionamiento de las 8 S
   public String Sss (String ss){
       String S[] = new String [8];
       String m[] = new String [8];
       String n[] = new String [8];
       String Si = "";
       //Se dividen las S y se les asignan valores a m y n
       for (int i = 0; i < 8; i++) {
           if (i == 0){
               m[i] = Character.toString(ss.charAt(0)) + Character.toString(ss.charAt(5));
               n[i] = ss.substring(1, 5);
                   if (n[i].equals(0000)){
                       if (m[i].equals("00")){
                           S[i]="1110";
                       } else if (m[i].equals("01")){
                           S[i]="0000";
                       }else if (m[i].equals("10")){
                           S[i]="0100";
                       }else if (m[i].equals("11")){
                           S[i]="1111";
                       }
                     
                   }else if (n[i].equals("0001")){
                       if (m[i].equals("00")){
                           S[i]="0100";
                       } else if (m[i].equals("01")){
                           S[i]="1111";
                       }else if (m[i].equals("10")){
                           S[i]="0001";
                       }else if (m[i].equals("11")){
                           S[i]="1100";
                       }
                   }else if (n[i].equals("0010")){
                       if (m[i].equals("00")){
                           S[i]="1101";
                       } else if (m[i].equals("01")){
                           S[i]="0111";
                       }else if (m[i].equals("10")){
                           S[i]="1110";
                       }else if (m[i].equals("11")){
                           S[i]="1000";
                       }
                   }else if (n[i].equals("0011")){
                     if (m[i].equals("00")){
                           S[i]="0001";
                       } else if (m[i].equals("01")){
                           S[i]="0100";
                       }else if (m[i].equals("10")){
                           S[i]="1000";
                       }else if (m[i].equals("11")){
                           S[i]="0010";
                       }
                   }else if (n[i].equals("0100")){
                     if (m[i].equals("00")){
                           S[i]="0010";
                       } else if (m[i].equals("01")){
                           S[i]="1110";
                       }else if (m[i].equals("10")){
                           S[i]="1101";
                       }else if (m[i].equals("11")){
                           S[i]="0100";
                       }
                   }else if (n[i].equals("0101")){
                     if (m[i].equals("00")){
                           S[i]="1111";
                       } else if (m[i].equals("01")){
                           S[i]="0010";
                       }else if (m[i].equals("10")){
                           S[i]="0110";
                       }else if (m[i].equals("11")){
                           S[i]="1001";
                       } 
                   }else if (n[i].equals("0110")){
                     if (m[i].equals("00")){
                           S[i]="1011";
                       } else if (m[i].equals("01")){
                           S[i]="1101";
                       }else if (m[i].equals("10")){
                           S[i]="0010";
                       }else if (m[i].equals("11")){
                           S[i]="0001";
                       }  
                   }else if (n[i].equals("0111")){
                      if (m[i].equals("00")){
                           S[i]="1000";
                       } else if (m[i].equals("01")){
                           S[i]="0001";
                       }else if (m[i].equals("10")){
                           S[i]="1011";
                       }else if (m[i].equals("11")){
                           S[i]="0111";
                       }
                   }else if (n[i].equals("1000")){
                      if (m[i].equals("00")){
                           S[i]="0011";
                       } else if (m[i].equals("01")){
                           S[i]="1010";
                       }else if (m[i].equals("10")){
                           S[i]="1111";
                       }else if (m[i].equals("11")){
                           S[i]="0101";
                       }
                   }else if (n[i].equals("1001")){
                      if (m[i].equals("00")){
                           S[i]="1010";
                       } else if (m[i].equals("01")){
                           S[i]="0110";
                       }else if (m[i].equals("10")){
                           S[i]="1100";
                       }else if (m[i].equals("11")){
                           S[i]="1011";
                       }
                   }else if (n[i].equals("1010")){
                      if (m[i].equals("00")){
                           S[i]="0110";
                       } else if (m[i].equals("01")){
                           S[i]="1100";
                       }else if (m[i].equals("10")){
                           S[i]="1001";
                       }else if (m[i].equals("11")){
                           S[i]="0011";
                       } 
                   }else if (n[i].equals("1011")){
                      if (m[i].equals("00")){
                           S[i]="1100";
                       } else if (m[i].equals("01")){
                           S[i]="1011";
                       }else if (m[i].equals("10")){
                           S[i]="0111";
                       }else if (m[i].equals("11")){
                           S[i]="1110";
                       }
                   }else if (n[i].equals("1100")){
                     if (m[i].equals("00")){
                           S[i]="0101";
                       } else if (m[i].equals("01")){
                           S[i]="1001";
                       }else if (m[i].equals("10")){
                           S[i]="0011";
                       }else if (m[i].equals("11")){
                           S[i]="1010";
                       }  
                   }else if (n[i].equals("1101")){
                    if (m[i].equals("00")){
                           S[i]="1001";
                       } else if (m[i].equals("01")){
                           S[i]="0101";
                       }else if (m[i].equals("10")){
                           S[i]="1010";
                       }else if (m[i].equals("11")){
                           S[i]="0";
                       }
                   }else if (n[i].equals("1110")){
                     if (m[i].equals("00")){
                           S[i]="0000";
                       } else if (m[i].equals("01")){
                           S[i]="0011";
                       }else if (m[i].equals("10")){
                           S[i]="0101";
                       }else if (m[i].equals("11")){
                           S[i]="0110";
                       }  
                   }else if (n[i].equals("1111")){
                    if (m[i].equals("00")){
                           S[i]="0111";
                       } else if (m[i].equals("01")){
                           S[i]="1000";
                       }else if (m[i].equals("10")){
                           S[i]="0000";
                       }else if (m[i].equals("11")){
                           S[i]="1101";
                       } 
                   }
           } else if (i == 1){
               m[i] = Character.toString(ss.charAt(6)) + Character.toString(ss.charAt(11));
               n[i] = ss.substring(7, 11);
               if (n[i].equals("0000")){
                       if (m[i].equals("00")){
                           S[i]="1111";
                       } else if (m[i].equals("01")){
                           S[i]="0011";
                       }else if (m[i].equals("10")){
                           S[i]="0000";
                       }else if (m[i].equals("11")){
                           S[i]="1101";
                       }
                     
                   }else if (n[i].equals("0001")){
                       if (m[i].equals("00")){
                           S[i]="0001";
                       } else if (m[i].equals("01")){
                           S[i]="1101";
                       }else if (m[i].equals("10")){
                           S[i]="1110";
                       }else if (m[i].equals("11")){
                           S[i]="1000";
                       }
                   }else if (n[i].equals("0010")){
                       if (m[i].equals("00")){
                           S[i]="1000";
                       } else if (m[i].equals("01")){
                           S[i]="0100";
                       }else if (m[i].equals("10")){
                           S[i]="0111";
                       }else if (m[i].equals("11")){
                           S[i]="1010";
                       }
                   }else if (n[i].equals("0011")){
                     if (m[i].equals("00")){
                           S[i]="1110";
                       } else if (m[i].equals("01")){
                           S[i]="0111";
                       }else if (m[i].equals("10")){
                           S[i]="1011";
                       }else if (m[i].equals("11")){
                           S[i]="0001";
                       }
                   }else if (n[i].equals("0100")){
                     if (m[i].equals("00")){
                           S[i]="0110";
                       } else if (m[i].equals("01")){
                           S[i]="1111";
                       }else if (m[i].equals("10")){
                           S[i]="1010";
                       }else if (m[i].equals("11")){
                           S[i]="0011";
                       }
                   }else if (n[i].equals("0101")){
                     if (m[i].equals("00")){
                           S[i]="1011";
                       } else if (m[i].equals("01")){
                           S[i]="0010";
                       }else if (m[i].equals("10")){
                           S[i]="0100";
                       }else if (m[i].equals("11")){
                           S[i]="1111";
                       } 
                   }else if (n[i].equals("0110")){
                     if (m[i].equals("00")){
                           S[i]="0011";
                       } else if (m[i].equals("01")){
                           S[i]="1000";
                       }else if (m[i].equals("10")){
                           S[i]="1101";
                       }else if (m[i].equals("11")){
                           S[i]="0100";
                       }  
                   }else if (n[i].equals("0111")){
                      if (m[i].equals("00")){
                           S[i]="0100";
                       } else if (m[i].equals("01")){
                           S[i]="1110";
                       }else if (m[i].equals("10")){
                           S[i]="0001";
                       }else if (m[i].equals("11")){
                           S[i]="0010";
                       }
                   }else if (n[i].equals("1000")){
                      if (m[i].equals("00")){
                           S[i]="1001";
                       } else if (m[i].equals("01")){
                           S[i]="1100";
                       }else if (m[i].equals("10")){
                           S[i]="0101";
                       }else if (m[i].equals("11")){
                           S[i]="1011";
                       }
                   }else if (n[i].equals("1001")){
                      if (m[i].equals("00")){
                           S[i]="0111";
                       } else if (m[i].equals("01")){
                           S[i]="0000";
                       }else if (m[i].equals("10")){
                           S[i]="1000";
                       }else if (m[i].equals("11")){
                           S[i]="0110";
                       }
                   }else if (n[i].equals("1010")){
                      if (m[i].equals("00")){
                           S[i]="0010";
                       } else if (m[i].equals("01")){
                           S[i]="0001";
                       }else if (m[i].equals("10")){
                           S[i]="1100";
                       }else if (m[i].equals("11")){
                           S[i]="0111";
                       } 
                   }else if (n[i].equals("1011")){
                      if (m[i].equals("00")){
                           S[i]="1101";
                       } else if (m[i].equals("01")){
                           S[i]="1010";
                       }else if (m[i].equals("10")){
                           S[i]="0110";
                       }else if (m[i].equals("11")){
                           S[i]="1100";
                       }
                   }else if (n[i].equals("1100")){
                     if (m[i].equals("00")){
                           S[i]="1100";
                       } else if (m[i].equals("01")){
                           S[i]="0110";
                       }else if (m[i].equals("10")){
                           S[i]="1001";
                       }else if (m[i].equals("11")){
                           S[i]="0000";
                       }  
                   }else if (n[i].equals("1101")){
                    if (m[i].equals("00")){
                           S[i]="0000";
                       } else if (m[i].equals("01")){
                           S[i]="1001";
                       }else if (m[i].equals("10")){
                           S[i]="0011";
                       }else if (m[i].equals("11")){
                           S[i]="0101";
                       }
                   }else if (n[i].equals("1110")){
                     if (m[i].equals("00")){
                           S[i]="0101";
                       } else if (m[i].equals("01")){
                           S[i]="1011";
                       }else if (m[i].equals("10")){
                           S[i]="0010";
                       }else if (m[i].equals("11")){
                           S[i]="1110";
                       }  
                   }else if (n[i].equals("1111")){
                    if (m[i].equals("00")){
                           S[i]="1010";
                       } else if (m[i].equals("01")){
                           S[i]="0101";
                       }else if (m[i].equals("10")){
                           S[i]="1111";
                       }else if (m[i].equals("11")){
                           S[i]="1001";
                       } 
                   }
           }
           else if (i == 2){
               m[i] = Character.toString(ss.charAt(12)) + Character.toString(ss.charAt(17));
               n[i] = ss.substring(13, 17);
               if (n[i].equals("0000")){
                       if (m[i].equals("00")){
                           S[i]="1010";
                       } else if (m[i].equals("01")){
                           S[i]="1101";
                       }else if (m[i].equals("10")){
                           S[i]="1101";
                       }else if (m[i].equals("11")){
                           S[i]="0001";
                       }
                     
                   }else if (n[i].equals("0001")){
                       if (m[i].equals("00")){
                           S[i]="0000";
                       } else if (m[i].equals("01")){
                           S[i]="0111";
                       }else if (m[i].equals("10")){
                           S[i]="0110";
                       }else if (m[i].equals("11")){
                           S[i]="1010";
                       }
                   }else if (n[i].equals("0010")){
                       if (m[i].equals("00")){
                           S[i]="1001";
                       } else if (m[i].equals("01")){
                           S[i]="0000";
                       }else if (m[i].equals("10")){
                           S[i]="0100";
                       }else if (m[i].equals("11")){
                           S[i]="1101";
                       }
                   }else if (n[i].equals("0011")){
                     if (m[i].equals("00")){
                           S[i]="1110";
                       } else if (m[i].equals("01")){
                           S[i]="1001";
                       }else if (m[i].equals("10")){
                           S[i]="1001";
                       }else if (m[i].equals("11")){
                           S[i]="0000";
                       }
                   }else if (n[i].equals("0100")){
                     if (m[i].equals("00")){
                           S[i]="0110";
                       } else if (m[i].equals("01")){
                           S[i]="0011";
                       }else if (m[i].equals("10")){
                           S[i]="1000";
                       }else if (m[i].equals("11")){
                           S[i]="0110";
                       }
                   }else if (n[i].equals("0101")){
                     if (m[i].equals("00")){
                           S[i]="0011";
                       } else if (m[i].equals("01")){
                           S[i]="0100";
                       }else if (m[i].equals("10")){
                           S[i]="1111";
                       }else if (m[i].equals("11")){
                           S[i]="1001";
                       } 
                   }else if (n[i].equals("0110")){
                     if (m[i].equals("00")){
                           S[i]="1111";
                       } else if (m[i].equals("01")){
                           S[i]="0110";
                       }else if (m[i].equals("10")){
                           S[i]="0011";
                       }else if (m[i].equals("11")){
                           S[i]="1000";
                       }  
                   }else if (n[i].equals("0111")){
                      if (m[i].equals("00")){
                           S[i]="0101";
                       } else if (m[i].equals("01")){
                           S[i]="1010";
                       }else if (m[i].equals("10")){
                           S[i]="0000";
                       }else if (m[i].equals("11")){
                           S[i]="0111";
                       }
                   }else if (n[i].equals("1000")){
                      if (m[i].equals("00")){
                           S[i]="0001";
                       } else if (m[i].equals("01")){
                           S[i]="0010";
                       }else if (m[i].equals("10")){
                           S[i]="1011";
                       }else if (m[i].equals("11")){
                           S[i]="0100";
                       }
                   }else if (n[i].equals("1001")){
                      if (m[i].equals("00")){
                           S[i]="1101";
                       } else if (m[i].equals("01")){
                           S[i]="1000";
                       }else if (m[i].equals("10")){
                           S[i]="0001";
                       }else if (m[i].equals("11")){
                           S[i]="1111";
                       }
                   }else if (n[i].equals("1010")){
                      if (m[i].equals("00")){
                           S[i]="1100";
                       } else if (m[i].equals("01")){
                           S[i]="0101";
                       }else if (m[i].equals("10")){
                           S[i]="0010";
                       }else if (m[i].equals("11")){
                           S[i]="1110";
                       } 
                   }else if (n[i].equals("1011")){
                      if (m[i].equals("00")){
                           S[i]="0111";
                       } else if (m[i].equals("01")){
                           S[i]="1110";
                       }else if (m[i].equals("10")){
                           S[i]="1100";
                       }else if (m[i].equals("11")){
                           S[i]="0011";
                       }
                   }else if (n[i].equals("1100")){
                     if (m[i].equals("00")){
                           S[i]="1011";
                       } else if (m[i].equals("01")){
                           S[i]="1100";
                       }else if (m[i].equals("10")){
                           S[i]="0101";
                       }else if (m[i].equals("11")){
                           S[i]="1011";
                       }  
                   }else if (n[i].equals("1101")){
                    if (m[i].equals("00")){
                           S[i]="0100";
                       } else if (m[i].equals("01")){
                           S[i]="1011";
                       }else if (m[i].equals("10")){
                           S[i]="1010";
                       }else if (m[i].equals("11")){
                           S[i]="0101";
                       }
                   }else if (n[i].equals("1110")){
                     if (m[i].equals("00")){
                           S[i]="0010";
                       } else if (m[i].equals("01")){
                           S[i]="1111";
                       }else if (m[i].equals("10")){
                           S[i]="1110";
                       }else if (m[i].equals("11")){
                           S[i]="0010";
                       }  
                   }else if (n[i].equals("1111")){
                    if (m[i].equals("00")){
                           S[i]="1000";
                       } else if (m[i].equals("01")){
                           S[i]="0001";
                       }else if (m[i].equals("10")){
                           S[i]="0111";
                       }else if (m[i].equals("11")){
                           S[i]="1100";
                       } 
                   }
           }
           else if (i == 3){
               m[i] = Character.toString(ss.charAt(18)) + Character.toString(ss.charAt(23));
               n[i] = ss.substring(19, 23);
               if (n[i].equals("0000")){
                       if (m[i].equals("00")){
                           S[i]="0111";
                       } else if (m[i].equals("01")){
                           S[i]="1101";
                       }else if (m[i].equals("10")){
                           S[i]="1010";
                       }else if (m[i].equals("11")){
                           S[i]="0011";
                       }
                     
                   }else if (n[i].equals("0001")){
                       if (m[i].equals("00")){
                           S[i]="1101";
                       } else if (m[i].equals("01")){
                           S[i]="1000";
                       }else if (m[i].equals("10")){
                           S[i]="0110";
                       }else if (m[i].equals("11")){
                           S[i]="1111";
                       }
                   }else if (n[i].equals("0010")){
                       if (m[i].equals("00")){
                           S[i]="1110";
                       } else if (m[i].equals("01")){
                           S[i]="1011";
                       }else if (m[i].equals("10")){
                           S[i]="1001";
                       }else if (m[i].equals("11")){
                           S[i]="000";
                       }
                   }else if (n[i].equals("0011")){
                     if (m[i].equals("00")){
                           S[i]="0011";
                       } else if (m[i].equals("01")){
                           S[i]="0101";
                       }else if (m[i].equals("10")){
                           S[i]="0000";
                       }else if (m[i].equals("11")){
                           S[i]="0110";
                       }
                   }else if (n[i].equals("0100")){
                     if (m[i].equals("00")){
                           S[i]="0000";
                       } else if (m[i].equals("01")){
                           S[i]="0110";
                       }else if (m[i].equals("10")){
                           S[i]="1100";
                       }else if (m[i].equals("11")){
                           S[i]="1010";
                       }
                   }else if (n[i].equals("0101")){
                     if (m[i].equals("00")){
                           S[i]="0110";
                       } else if (m[i].equals("01")){
                           S[i]="1111";
                       }else if (m[i].equals("10")){
                           S[i]="1011";
                       }else if (m[i].equals("11")){
                           S[i]="0001";
                       } 
                   }else if (n[i].equals("0110")){
                     if (m[i].equals("00")){
                           S[i]="1001";
                       } else if (m[i].equals("01")){
                           S[i]="0000";
                       }else if (m[i].equals("10")){
                           S[i]="0111";
                       }else if (m[i].equals("11")){
                           S[i]="1101";
                       }  
                   }else if (n[i].equals("0111")){
                      if (m[i].equals("00")){
                           S[i]="1010";
                       } else if (m[i].equals("01")){
                           S[i]="0011";
                       }else if (m[i].equals("10")){
                           S[i]="1101";
                       }else if (m[i].equals("11")){
                           S[i]="1000";
                       }
                   }else if (n[i].equals("1000")){
                      if (m[i].equals("00")){
                           S[i]="00001";
                       } else if (m[i].equals("01")){
                           S[i]="0100";
                       }else if (m[i].equals("10")){
                           S[i]="1111";
                       }else if (m[i].equals("11")){
                           S[i]="1001";
                       }
                   }else if (n[i].equals("1001")){
                      if (m[i].equals("00")){
                           S[i]="0010";
                       } else if (m[i].equals("01")){
                           S[i]="0111";
                       }else if (m[i].equals("10")){
                           S[i]="0001";
                       }else if (m[i].equals("11")){
                           S[i]="0100";
                       }
                   }else if (n[i].equals("1010")){
                      if (m[i].equals("00")){
                           S[i]="1000";
                       } else if (m[i].equals("01")){
                           S[i]="0010";
                       }else if (m[i].equals("10")){
                           S[i]="0011";
                       }else if (m[i].equals("11")){
                           S[i]="0101";
                       } 
                   }else if (n[i].equals("1011")){
                      if (m[i].equals("00")){
                           S[i]="0101";
                       } else if (m[i].equals("01")){
                           S[i]="1100";
                       }else if (m[i].equals("10")){
                           S[i]="1110";
                       }else if (m[i].equals("11")){
                           S[i]="1011";
                       }
                   }else if (n[i].equals("1100")){
                     if (m[i].equals("00")){
                           S[i]="1011";
                       } else if (m[i].equals("01")){
                           S[i]="0001";
                       }else if (m[i].equals("10")){
                           S[i]="0101";
                       }else if (m[i].equals("11")){
                           S[i]="1100";
                       }  
                   }else if (n[i].equals("1101")){
                    if (m[i].equals("00")){
                           S[i]="1100";
                       } else if (m[i].equals("01")){
                           S[i]="1010";
                       }else if (m[i].equals("10")){
                           S[i]="0010";
                       }else if (m[i].equals("11")){
                           S[i]="0111";
                       }
                   }else if (n[i].equals("1110")){
                     if (m[i].equals("00")){
                           S[i]="0100";
                       } else if (m[i].equals("01")){
                           S[i]="1110";
                       }else if (m[i].equals("10")){
                           S[i]="1000";
                       }else if (m[i].equals("11")){
                           S[i]="0010";
                       }  
                   }else if (n[i].equals("1111")){
                    if (m[i].equals("00")){
                           S[i]="1111";
                       } else if (m[i].equals("01")){
                           S[i]="1001";
                       }else if (m[i].equals("10")){
                           S[i]="0100";
                       }else if (m[i].equals("11")){
                           S[i]="1110";
                       } 
                   }
           }
           else if (i == 4){
               m[i] = Character.toString(ss.charAt(24)) + Character.toString(ss.charAt(29));
               n[i] = ss.substring(25, 29);
               if (n[i].equals("0000")){
                       if (m[i].equals("00")){
                           S[i]="0010";
                       } else if (m[i].equals("01")){
                           S[i]="1110";
                       }else if (m[i].equals("10")){
                           S[i]="0100";
                       }else if (m[i].equals("11")){
                           S[i]="1011";
                       }
                     
                   }else if (n[i].equals("0001")){
                       if (m[i].equals("00")){
                           S[i]="1100";
                       } else if (m[i].equals("01")){
                           S[i]="1011";
                       }else if (m[i].equals("10")){
                           S[i]="0010";
                       }else if (m[i].equals("11")){
                           S[i]="1000";
                       }
                   }else if (n[i].equals("0010")){
                       if (m[i].equals("00")){
                           S[i]="0100";
                       } else if (m[i].equals("01")){
                           S[i]="0010";
                       }else if (m[i].equals("10")){
                           S[i]="0001";
                       }else if (m[i].equals("11")){
                           S[i]="1100";
                       }
                   }else if (n[i].equals("0011")){
                     if (m[i].equals("00")){
                           S[i]="0001";
                       } else if (m[i].equals("01")){
                           S[i]="1100";
                       }else if (m[i].equals("10")){
                           S[i]="1011";
                       }else if (m[i].equals("11")){
                           S[i]="0111";
                       }
                   }else if (n[i].equals("0100")){
                     if (m[i].equals("00")){
                           S[i]="0111";
                       } else if (m[i].equals("01")){
                           S[i]="0100";
                       }else if (m[i].equals("10")){
                           S[i]="1010";
                       }else if (m[i].equals("11")){
                           S[i]="0001";
                       }
                   }else if (n[i].equals("0101")){
                     if (m[i].equals("00")){
                           S[i]="1010";
                       } else if (m[i].equals("01")){
                           S[i]="0111";
                       }else if (m[i].equals("10")){
                           S[i]="1101";
                       }else if (m[i].equals("11")){
                           S[i]="1111";
                       } 
                   }else if (n[i].equals("0110")){
                     if (m[i].equals("00")){
                           S[i]="1011";
                       } else if (m[i].equals("01")){
                           S[i]="1101";
                       }else if (m[i].equals("10")){
                           S[i]="0111";
                       }else if (m[i].equals("11")){
                           S[i]="0010";
                       }  
                   }else if (n[i].equals("0111")){
                      if (m[i].equals("00")){
                           S[i]="0110";
                       } else if (m[i].equals("01")){
                           S[i]="0001";
                       }else if (m[i].equals("10")){
                           S[i]="1000";
                       }else if (m[i].equals("11")){
                           S[i]="1101";
                       }
                   }else if (n[i].equals("1000")){
                      if (m[i].equals("00")){
                           S[i]="1000";
                       } else if (m[i].equals("01")){
                           S[i]="0101";
                       }else if (m[i].equals("10")){
                           S[i]="1111";
                       }else if (m[i].equals("11")){
                           S[i]="0110";
                       }
                   }else if (n[i].equals("1001")){
                      if (m[i].equals("00")){
                           S[i]="0101";
                       } else if (m[i].equals("01")){
                           S[i]="0000";
                       }else if (m[i].equals("10")){
                           S[i]="1001";
                       }else if (m[i].equals("11")){
                           S[i]="1111";
                       }
                   }else if (n[i].equals("1010")){
                      if (m[i].equals("00")){
                           S[i]="0011";
                       } else if (m[i].equals("01")){
                           S[i]="1111";
                       }else if (m[i].equals("10")){
                           S[i]="1100";
                       }else if (m[i].equals("11")){
                           S[i]="0000";
                       } 
                   }else if (n[i].equals("1011")){
                      if (m[i].equals("00")){
                           S[i]="1111";
                       } else if (m[i].equals("01")){
                           S[i]="1010";
                       }else if (m[i].equals("10")){
                           S[i]="0101";
                       }else if (m[i].equals("11")){
                           S[i]="1001";
                       }
                   }else if (n[i].equals("1100")){
                     if (m[i].equals("00")){
                           S[i]="1101";
                       } else if (m[i].equals("01")){
                           S[i]="0011";
                       }else if (m[i].equals("10")){
                           S[i]="0110";
                       }else if (m[i].equals("11")){
                           S[i]="1010";
                       }  
                   }else if (n[i].equals("1101")){
                    if (m[i].equals("00")){
                           S[i]="0000";
                       } else if (m[i].equals("01")){
                           S[i]="1001";
                       }else if (m[i].equals("10")){
                           S[i]="0011";
                       }else if (m[i].equals("11")){
                           S[i]="0100";
                       }
                   }else if (n[i].equals("1110")){
                     if (m[i].equals("00")){
                           S[i]="1110";
                       } else if (m[i].equals("01")){
                           S[i]="1000";
                       }else if (m[i].equals("10")){
                           S[i]="0000";
                       }else if (m[i].equals("11")){
                           S[i]="0101";
                       }  
                   }else if (n[i].equals("1111")){
                    if (m[i].equals("00")){
                           S[i]="1001";
                       } else if (m[i].equals("01")){
                           S[i]="0110";
                       }else if (m[i].equals("10")){
                           S[i]="1110";
                       }else if (m[i].equals("11")){
                           S[i]="0011";
                       } 
                   }
           }
           else if (i == 5){
               m[i] = Character.toString(ss.charAt(30)) + Character.toString(ss.charAt(35));
               n[i] = ss.substring(31, 35);
               if (n[i].equals("0000")){
                       if (m[i].equals("00")){
                           S[i]="1100";
                       } else if (m[i].equals("01")){
                           S[i]="1010";
                       }else if (m[i].equals("10")){
                           S[i]="1001";
                       }else if (m[i].equals("11")){
                           S[i]="0100";
                       }
                     
                   }else if (n[i].equals("0001")){
                       if (m[i].equals("00")){
                           S[i]="0001";
                       } else if (m[i].equals("01")){
                           S[i]="1111";
                       }else if (m[i].equals("10")){
                           S[i]="1110";
                       }else if (m[i].equals("11")){
                           S[i]="0011";
                       }
                   }else if (n[i].equals("0010")){
                       if (m[i].equals("00")){
                           S[i]="1010";
                       } else if (m[i].equals("01")){
                           S[i]="0100";
                       }else if (m[i].equals("10")){
                           S[i]="1111";
                       }else if (m[i].equals("11")){
                           S[i]="0010";
                       }
                   }else if (n[i].equals("0011")){
                     if (m[i].equals("00")){
                           S[i]="1111";
                       } else if (m[i].equals("01")){
                           S[i]="0010";
                       }else if (m[i].equals("10")){
                           S[i]="0101";
                       }else if (m[i].equals("11")){
                           S[i]="1100";
                       }
                   }else if (n[i].equals("0100")){
                     if (m[i].equals("00")){
                           S[i]="1001";
                       } else if (m[i].equals("01")){
                           S[i]="0111";
                       }else if (m[i].equals("10")){
                           S[i]="0010";
                       }else if (m[i].equals("11")){
                           S[i]="1001";
                       }
                   }else if (n[i].equals("0101")){
                     if (m[i].equals("00")){
                           S[i]="0010";
                       } else if (m[i].equals("01")){
                           S[i]="1100";
                       }else if (m[i].equals("10")){
                           S[i]="1000";
                       }else if (m[i].equals("11")){
                           S[i]="0101";
                       } 
                   }else if (n[i].equals("0110")){
                     if (m[i].equals("00")){
                           S[i]="0110";
                       } else if (m[i].equals("01")){
                           S[i]="1001";
                       }else if (m[i].equals("10")){
                           S[i]="1100";
                       }else if (m[i].equals("11")){
                           S[i]="1111";
                       }  
                   }else if (n[i].equals("0111")){
                      if (m[i].equals("00")){
                           S[i]="1000";
                       } else if (m[i].equals("01")){
                           S[i]="0101";
                       }else if (m[i].equals("10")){
                           S[i]="0011";
                       }else if (m[i].equals("11")){
                           S[i]="1010";
                       }
                   }else if (n[i].equals("1000")){
                      if (m[i].equals("00")){
                           S[i]="0000";
                       } else if (m[i].equals("01")){
                           S[i]="0110";
                       }else if (m[i].equals("10")){
                           S[i]="0111";
                       }else if (m[i].equals("11")){
                           S[i]="1011";
                       }
                   }else if (n[i].equals("1001")){
                      if (m[i].equals("00")){
                           S[i]="1101";
                       } else if (m[i].equals("01")){
                           S[i]="0001";
                       }else if (m[i].equals("10")){
                           S[i]="0000";
                       }else if (m[i].equals("11")){
                           S[i]="1110";
                       }
                   }else if (n[i].equals("1010")){
                      if (m[i].equals("00")){
                           S[i]="0011";
                       } else if (m[i].equals("01")){
                           S[i]="1101";
                       }else if (m[i].equals("10")){
                           S[i]="0100";
                       }else if (m[i].equals("11")){
                           S[i]="0001";
                       } 
                   }else if (n[i].equals("1011")){
                      if (m[i].equals("00")){
                           S[i]="0100";
                       } else if (m[i].equals("01")){
                           S[i]="1110";
                       }else if (m[i].equals("10")){
                           S[i]="1010";
                       }else if (m[i].equals("11")){
                           S[i]="0111";
                       }
                   }else if (n[i].equals("1100")){
                     if (m[i].equals("00")){
                           S[i]="1110";
                       } else if (m[i].equals("01")){
                           S[i]="0000";
                       }else if (m[i].equals("10")){
                           S[i]="0001";
                       }else if (m[i].equals("11")){
                           S[i]="0110";
                       }  
                   }else if (n[i].equals("1101")){
                    if (m[i].equals("00")){
                           S[i]="0111";
                       } else if (m[i].equals("01")){
                           S[i]="1011";
                       }else if (m[i].equals("10")){
                           S[i]="1101";
                       }else if (m[i].equals("11")){
                           S[i]="0000";
                       }
                   }else if (n[i].equals("1110")){
                     if (m[i].equals("00")){
                           S[i]="0101";
                       } else if (m[i].equals("01")){
                           S[i]="0011";
                       }else if (m[i].equals("10")){
                           S[i]="1011";
                       }else if (m[i].equals("11")){
                           S[i]="1000";
                       }  
                   }else if (n[i].equals("1111")){
                    if (m[i].equals("00")){
                           S[i]="1011";
                       } else if (m[i].equals("01")){
                           S[i]="1000";
                       }else if (m[i].equals("10")){
                           S[i]="0110";
                       }else if (m[i].equals("11")){
                           S[i]="1101";
                       } 
                   }
           }
           else if (i == 6){
               m[i] = Character.toString(ss.charAt(36)) + Character.toString(ss.charAt(41));
               n[i] = ss.substring(37, 41);
               if (n[i].equals("0000")){
                       if (m[i].equals("00")){
                           S[i]="0100";
                       } else if (m[i].equals("01")){
                           S[i]="1101";
                       }else if (m[i].equals("10")){
                           S[i]="0001";
                       }else if (m[i].equals("11")){
                           S[i]="0110";
                       }
                     
                   }else if (n[i].equals("0001")){
                       if (m[i].equals("00")){
                           S[i]="1011";
                       } else if (m[i].equals("01")){
                           S[i]="0000";
                       }else if (m[i].equals("10")){
                           S[i]="0100";
                       }else if (m[i].equals("11")){
                           S[i]="1011";
                       }
                   }else if (n[i].equals("0010")){
                       if (m[i].equals("00")){
                           S[i]="0010";
                       } else if (m[i].equals("01")){
                           S[i]="1011";
                       }else if (m[i].equals("10")){
                           S[i]="1011";
                       }else if (m[i].equals("11")){
                           S[i]="1101";
                       }
                   }else if (n[i].equals("0011")){
                     if (m[i].equals("00")){
                           S[i]="1110";
                       } else if (m[i].equals("01")){
                           S[i]="0111";
                       }else if (m[i].equals("10")){
                           S[i]="1101";
                       }else if (m[i].equals("11")){
                           S[i]="1000";
                       }
                   }else if (n[i].equals("0100")){
                     if (m[i].equals("00")){
                           S[i]="1111";
                       } else if (m[i].equals("01")){
                           S[i]="0100";
                       }else if (m[i].equals("10")){
                           S[i]="1100";
                       }else if (m[i].equals("11")){
                           S[i]="0001";
                       }
                   }else if (n[i].equals("0101")){
                     if (m[i].equals("00")){
                           S[i]="0000";
                       } else if (m[i].equals("01")){
                           S[i]="1001";
                       }else if (m[i].equals("10")){
                           S[i]="0011";
                       }else if (m[i].equals("11")){
                           S[i]="0100";
                       } 
                   }else if (n[i].equals("0110")){
                     if (m[i].equals("00")){
                           S[i]="1000";
                       } else if (m[i].equals("01")){
                           S[i]="0001";
                       }else if (m[i].equals("10")){
                           S[i]="0111";
                       }else if (m[i].equals("11")){
                           S[i]="1010";
                       }  
                   }else if (n[i].equals("0111")){
                      if (m[i].equals("00")){
                           S[i]="1101";
                       } else if (m[i].equals("01")){
                           S[i]="1010";
                       }else if (m[i].equals("10")){
                           S[i]="1110";
                       }else if (m[i].equals("11")){
                           S[i]="0111";
                       }
                   }else if (n[i].equals("1000")){
                      if (m[i].equals("00")){
                           S[i]="0011";
                       } else if (m[i].equals("01")){
                           S[i]="1110";
                       }else if (m[i].equals("10")){
                           S[i]="1010";
                       }else if (m[i].equals("11")){
                           S[i]="1001";
                       }
                   }else if (n[i].equals("1001")){
                      if (m[i].equals("00")){
                           S[i]="1100";
                       } else if (m[i].equals("01")){
                           S[i]="0011";
                       }else if (m[i].equals("10")){
                           S[i]="1111";
                       }else if (m[i].equals("11")){
                           S[i]="0101";
                       }
                   }else if (n[i].equals("1010")){
                      if (m[i].equals("00")){
                           S[i]="1001";
                       } else if (m[i].equals("01")){
                           S[i]="0101";
                       }else if (m[i].equals("10")){
                           S[i]="0110";
                       }else if (m[i].equals("11")){
                           S[i]="0000";
                       } 
                   }else if (n[i].equals("1011")){
                      if (m[i].equals("00")){
                           S[i]="0111";
                       } else if (m[i].equals("01")){
                           S[i]="1100";
                       }else if (m[i].equals("10")){
                           S[i]="1000";
                       }else if (m[i].equals("11")){
                           S[i]="1111";
                       }
                   }else if (n[i].equals("1100")){
                     if (m[i].equals("00")){
                           S[i]="0101";
                       } else if (m[i].equals("01")){
                           S[i]="0010";
                       }else if (m[i].equals("10")){
                           S[i]="0000";
                       }else if (m[i].equals("11")){
                           S[i]="1110";
                       }  
                   }else if (n[i].equals("1101")){
                    if (m[i].equals("00")){
                           S[i]="1010";
                       } else if (m[i].equals("01")){
                           S[i]="1111";
                       }else if (m[i].equals("10")){
                           S[i]="0101";
                       }else if (m[i].equals("11")){
                           S[i]="0010";
                       }
                   }else if (n[i].equals("1110")){
                     if (m[i].equals("00")){
                           S[i]="0110";
                       } else if (m[i].equals("01")){
                           S[i]="1000";
                       }else if (m[i].equals("10")){
                           S[i]="1001";
                       }else if (m[i].equals("11")){
                           S[i]="0011";
                       }  
                   }else if (n[i].equals("1111")){
                    if (m[i].equals("00")){
                           S[i]="0001";
                       } else if (m[i].equals("01")){
                           S[i]="0110";
                       }else if (m[i].equals("10")){
                           S[i]="0010";
                       }else if (m[i].equals("11")){
                           S[i]="1100";
                       } 
                   }
           }
           else if (i == 7){
               m[i] = Character.toString(ss.charAt(42)) + Character.toString(ss.charAt(47));
               n[i] = ss.substring(43, 47);
               if (n[i].equals("0000")){
                       if (m[i].equals("00")){
                           S[i]="1101";
                       } else if (m[i].equals("01")){
                           S[i]="0001";
                       }else if (m[i].equals("10")){
                           S[i]="0111";
                       }else if (m[i].equals("11")){
                           S[i]="0010";
                       }
                     
                   }else if (n[i].equals("0001")){
                       if (m[i].equals("00")){
                           S[i]="0010";
                       } else if (m[i].equals("01")){
                           S[i]="1111";
                       }else if (m[i].equals("10")){
                           S[i]="1011";
                       }else if (m[i].equals("11")){
                           S[i]="0001";
                       }
                   }else if (n[i].equals("0010")){
                       if (m[i].equals("00")){
                           S[i]="1000";
                       } else if (m[i].equals("01")){
                           S[i]="1101";
                       }else if (m[i].equals("10")){
                           S[i]="0100";
                       }else if (m[i].equals("11")){
                           S[i]="1110";
                       }
                   }else if (n[i].equals("0011")){
                     if (m[i].equals("00")){
                           S[i]="0100";
                       } else if (m[i].equals("01")){
                           S[i]="1000";
                       }else if (m[i].equals("10")){
                           S[i]="0001";
                       }else if (m[i].equals("11")){
                           S[i]="0111";
                       }
                   }else if (n[i].equals("0100")){
                     if (m[i].equals("00")){
                           S[i]="0110";
                       } else if (m[i].equals("01")){
                           S[i]="1010";
                       }else if (m[i].equals("10")){
                           S[i]="1001";
                       }else if (m[i].equals("11")){
                           S[i]="0100";
                       }
                   }else if (n[i].equals("0101")){
                     if (m[i].equals("00")){
                           S[i]="1111";
                       } else if (m[i].equals("01")){
                           S[i]="0011";
                       }else if (m[i].equals("10")){
                           S[i]="1100";
                       }else if (m[i].equals("11")){
                           S[i]="1010";
                       } 
                   }else if (n[i].equals("0110")){
                     if (m[i].equals("00")){
                           S[i]="1011";
                       } else if (m[i].equals("01")){
                           S[i]="0111";
                       }else if (m[i].equals("10")){
                           S[i]="1110";
                       }else if (m[i].equals("11")){
                           S[i]="1000";
                       }  
                   }else if (n[i].equals("0111")){
                      if (m[i].equals("00")){
                           S[i]="0001";
                       } else if (m[i].equals("01")){
                           S[i]="0100";
                       }else if (m[i].equals("10")){
                           S[i]="0010";
                       }else if (m[i].equals("11")){
                           S[i]="1101";
                       }
                   }else if (n[i].equals("1000")){
                      if (m[i].equals("00")){
                           S[i]="1010";
                       } else if (m[i].equals("01")){
                           S[i]="1100";
                       }else if (m[i].equals("10")){
                           S[i]="0000";
                       }else if (m[i].equals("11")){
                           S[i]="1111";
                       }
                   }else if (n[i].equals("1001")){
                      if (m[i].equals("00")){
                           S[i]="1001";
                       } else if (m[i].equals("01")){
                           S[i]="0101";
                       }else if (m[i].equals("10")){
                           S[i]="0110";
                       }else if (m[i].equals("11")){
                           S[i]="1100";
                       }
                   }else if (n[i].equals("1010")){
                      if (m[i].equals("00")){
                           S[i]="0011";
                       } else if (m[i].equals("01")){
                           S[i]="0110";
                       }else if (m[i].equals("10")){
                           S[i]="1010";
                       }else if (m[i].equals("11")){
                           S[i]="1001";
                       } 
                   }else if (n[i].equals("1011")){
                      if (m[i].equals("00")){
                           S[i]="1110";
                       } else if (m[i].equals("01")){
                           S[i]="1011";
                       }else if (m[i].equals("10")){
                           S[i]="1101";
                       }else if (m[i].equals("11")){
                           S[i]="0000";
                       }
                   }else if (n[i].equals("1100")){
                     if (m[i].equals("00")){
                           S[i]="0101";
                       } else if (m[i].equals("01")){
                           S[i]="0000";
                       }else if (m[i].equals("10")){
                           S[i]="1111";
                       }else if (m[i].equals("11")){
                           S[i]="0011";
                       }  
                   }else if (n[i].equals("1101")){
                    if (m[i].equals("00")){
                           S[i]="0000";
                       } else if (m[i].equals("01")){
                           S[i]="1110";
                       }else if (m[i].equals("10")){
                           S[i]="0011";
                       }else if (m[i].equals("11")){
                           S[i]="0101";
                       }
                   }else if (n[i].equals("1110")){
                     if (m[i].equals("00")){
                           S[i]="1100";
                       } else if (m[i].equals("01")){
                           S[i]="1001";
                       }else if (m[i].equals("10")){
                           S[i]="0101";
                       }else if (m[i].equals("11")){
                           S[i]="0110";
                       }  
                   }else if (n[i].equals("1111")){
                    if (m[i].equals("00")){
                           S[i]="0111";
                       } else if (m[i].equals("01")){
                           S[i]="0010";
                       }else if (m[i].equals("10")){
                           S[i]="1000";
                       }else if (m[i].equals("11")){
                           S[i]="1011";
                       } 
                   }
           }
           Si+= S[i];
           //System.out.print(Si);
       }
       return Si;
   }
   
   //Método que hace la permutación del dato
   public String PermutacionInicial(String ini){
      ini = Character.toString(ini.charAt(57)) + Character.toString(ini.charAt(49)) + Character.toString(ini.charAt(41)) + Character.toString(ini.charAt(33)) +
      Character.toString(ini.charAt(25)) + Character.toString(ini.charAt(17)) + Character.toString(ini.charAt(9)) + Character.toString(ini.charAt(1)) +
                    
     Character.toString(ini.charAt(59)) + Character.toString(ini.charAt(51)) + Character.toString(ini.charAt(43)) + Character.toString(ini.charAt(35)) +
     Character.toString(ini.charAt(27)) + Character.toString(ini.charAt(19)) + Character.toString(ini.charAt(11)) + Character.toString(ini.charAt(3)) +
                    
     Character.toString(ini.charAt(61)) + Character.toString(ini.charAt(53)) + Character.toString(ini.charAt(45)) + Character.toString(ini.charAt(37)) +
     Character.toString(ini.charAt(29)) + Character.toString(ini.charAt(21)) + Character.toString(ini.charAt(13)) + Character.toString(ini.charAt(5)) +
                    
     Character.toString(ini.charAt(63)) + Character.toString(ini.charAt(55)) + Character.toString(ini.charAt(47)) + Character.toString(ini.charAt(39)) +
     Character.toString(ini.charAt(31)) + Character.toString(ini.charAt(23)) + Character.toString(ini.charAt(15)) + Character.toString(ini.charAt(7)) +
                    
     Character.toString(ini.charAt(56)) + Character.toString(ini.charAt(48)) + Character.toString(ini.charAt(40)) + Character.toString(ini.charAt(32)) +
     Character.toString(ini.charAt(24)) + Character.toString(ini.charAt(16)) + Character.toString(ini.charAt(8)) + Character.toString(ini.charAt(0)) +
                    
     Character.toString(ini.charAt(58)) + Character.toString(ini.charAt(50)) + Character.toString(ini.charAt(42)) + Character.toString(ini.charAt(34)) +
     Character.toString(ini.charAt(26)) + Character.toString(ini.charAt(18)) + Character.toString(ini.charAt(10)) + Character.toString(ini.charAt(2)) +
                    
     Character.toString(ini.charAt(60)) + Character.toString(ini.charAt(52)) + Character.toString(ini.charAt(44)) + Character.toString(ini.charAt(36)) +
     Character.toString(ini.charAt(28)) + Character.toString(ini.charAt(20)) + Character.toString(ini.charAt(12)) + Character.toString(ini.charAt(4)) +
                    
     Character.toString(ini.charAt(62)) + Character.toString(ini.charAt(54)) + Character.toString(ini.charAt(46)) + Character.toString(ini.charAt(38)) +
     Character.toString(ini.charAt(10)) + Character.toString(ini.charAt(22)) + Character.toString(ini.charAt(14)) + Character.toString(ini.charAt(6));
    // System.out.println(PI);
    return ini;
   }
   
   //Método que hace la permutación final y despliega el cifrado
   public String PermutacionFinal (String fin){
       fin = Character.toString(fin.charAt(39)) + Character.toString(fin.charAt(7)) +
            Character.toString(fin.charAt(47)) + Character.toString(fin.charAt(15)) +
            Character.toString(fin.charAt(55)) + Character.toString(fin.charAt(23)) +
            Character.toString(fin.charAt(63)) + Character.toString(fin.charAt(31)) +
               
           Character.toString(fin.charAt(38)) + Character.toString(fin.charAt(6)) +
           Character.toString(fin.charAt(46)) + Character.toString(fin.charAt(14)) +
           Character.toString(fin.charAt(54)) + Character.toString(fin.charAt(22)) +
           Character.toString(fin.charAt(62)) + Character.toString(fin.charAt(30)) +
               
           Character.toString(fin.charAt(37)) + Character.toString(fin.charAt(5)) +
           Character.toString(fin.charAt(45)) + Character.toString(fin.charAt(13)) +
           Character.toString(fin.charAt(53)) + Character.toString(fin.charAt(21)) +
           Character.toString(fin.charAt(61)) + Character.toString(fin.charAt(29)) +
               
           Character.toString(fin.charAt(36)) + Character.toString(fin.charAt(4)) +
           Character.toString(fin.charAt(44)) + Character.toString(fin.charAt(12)) +
           Character.toString(fin.charAt(52)) + Character.toString(fin.charAt(20)) +
           Character.toString(fin.charAt(60)) + Character.toString(fin.charAt(28)) +
               
           Character.toString(fin.charAt(35)) + Character.toString(fin.charAt(3)) +
           Character.toString(fin.charAt(43)) + Character.toString(fin.charAt(11)) +
           Character.toString(fin.charAt(51)) + Character.toString(fin.charAt(19)) +
           Character.toString(fin.charAt(59)) + Character.toString(fin.charAt(27)) +
               
           Character.toString(fin.charAt(34)) + Character.toString(fin.charAt(2)) +
           Character.toString(fin.charAt(42)) + Character.toString(fin.charAt(10)) +
           Character.toString(fin.charAt(50)) + Character.toString(fin.charAt(18)) +
           Character.toString(fin.charAt(58)) + Character.toString(fin.charAt(26)) +
               
           Character.toString(fin.charAt(33)) + Character.toString(fin.charAt(1)) +
           Character.toString(fin.charAt(41)) + Character.toString(fin.charAt(9)) +
           Character.toString(fin.charAt(49)) + Character.toString(fin.charAt(17)) +
           Character.toString(fin.charAt(57)) + Character.toString(fin.charAt(25)) +
               
           Character.toString(fin.charAt(32)) + Character.toString(fin.charAt(0)) +
           Character.toString(fin.charAt(40)) + Character.toString(fin.charAt(8)) +
           Character.toString(fin.charAt(48)) + Character.toString(fin.charAt(16)) +
           Character.toString(fin.charAt(56)) + Character.toString(fin.charAt(24));
           return fin;
   }
}
    
