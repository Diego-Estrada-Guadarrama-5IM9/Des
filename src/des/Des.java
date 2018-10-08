//ESTRADA GUADARRAMA DIEGO
//5IM9
package Des;

public class Des  {
  String llave = "0001001100110100010101110111100110011011101111001101111111110001";
  String dato =  "0000000100100011010001010110011110001001101010111100110111101111";
  String E[] = new String [17];
  String P[] = new String [17];
  String C[] = new String [17] ;
  String Ki[] = new String [17];
  String Kf[] = new String [17];
  String D[] = new String [17];
   
   public void Des(/*String llave*/){
	   //inicializamos C0
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
                    
                    Character.toString(Ki[i].charAt(33)) + Character.toString(Ki[i].charAt(53)) +
                    Character.toString(Ki[i].charAt(45)) + Character.toString(Ki[i].charAt(41)) +
                    Character.toString(Ki[i].charAt(49)) + Character.toString(Ki[i].charAt(35)) +
                    Character.toString(Ki[i].charAt(28)) + Character.toString(Ki[i].charAt(31));
            //System.out.println("K"+i+"= "+Kf[i]);

       }
          //Se hace la permutación del dato
            String PID = Character.toString(dato.charAt(57)) + Character.toString(dato.charAt(49)) + Character.toString(dato.charAt(41)) + Character.toString(dato.charAt(33)) +
                    Character.toString(dato.charAt(25)) + Character.toString(dato.charAt(17)) + Character.toString(dato.charAt(9)) + Character.toString(dato.charAt(1)) +
                    
                    Character.toString(dato.charAt(59)) + Character.toString(dato.charAt(51)) + Character.toString(dato.charAt(43)) + Character.toString(dato.charAt(35)) +
                    Character.toString(dato.charAt(27)) + Character.toString(dato.charAt(19)) + Character.toString(dato.charAt(11)) + Character.toString(dato.charAt(3)) +
                    
                    Character.toString(dato.charAt(61)) + Character.toString(dato.charAt(53)) + Character.toString(dato.charAt(45)) + Character.toString(dato.charAt(37)) +
                    Character.toString(dato.charAt(29)) + Character.toString(dato.charAt(21)) + Character.toString(dato.charAt(13)) + Character.toString(dato.charAt(5)) +
                    
                    Character.toString(dato.charAt(63)) + Character.toString(dato.charAt(55)) + Character.toString(dato.charAt(47)) + Character.toString(dato.charAt(39)) +
                    Character.toString(dato.charAt(31)) + Character.toString(dato.charAt(23)) + Character.toString(dato.charAt(15)) + Character.toString(dato.charAt(7)) +
                    
                    Character.toString(dato.charAt(56)) + Character.toString(dato.charAt(48)) + Character.toString(dato.charAt(40)) + Character.toString(dato.charAt(32)) +
                    Character.toString(dato.charAt(24)) + Character.toString(dato.charAt(16)) + Character.toString(dato.charAt(8)) + Character.toString(dato.charAt(0)) +
                    
                    Character.toString(dato.charAt(58)) + Character.toString(dato.charAt(50)) + Character.toString(dato.charAt(42)) + Character.toString(dato.charAt(34)) +
                    Character.toString(dato.charAt(26)) + Character.toString(dato.charAt(18)) + Character.toString(dato.charAt(10)) + Character.toString(dato.charAt(2)) +
                    
                    Character.toString(dato.charAt(60)) + Character.toString(dato.charAt(52)) + Character.toString(dato.charAt(44)) + Character.toString(dato.charAt(36)) +
                    Character.toString(dato.charAt(28)) + Character.toString(dato.charAt(20)) + Character.toString(dato.charAt(12)) + Character.toString(dato.charAt(4)) +
                    
                    Character.toString(dato.charAt(62)) + Character.toString(dato.charAt(54)) + Character.toString(dato.charAt(46)) + Character.toString(dato.charAt(38)) +
                    Character.toString(dato.charAt(10)) + Character.toString(dato.charAt(22)) + Character.toString(dato.charAt(14)) + Character.toString(dato.charAt(6));
            
            //System.out.println(PID);
            
    }
   
   public void Xor(String P1, String P2){
       String RXor;
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
           RXor = Character.toString(RPrueba[i]);
           System.out.print(RXor);
       }
   }
}
