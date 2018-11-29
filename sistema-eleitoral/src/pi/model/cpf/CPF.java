package pi.model.cpf;

public class CPF {
	private static String calcDigVerif(String num) {  
        Integer primDig, segDig;  
        int soma = 0, peso = 10;  
        for (int i = 0; i < num.length(); i++)  
                soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;  
        if (soma % 11 == 0 | soma % 11 == 1)  
            primDig = new Integer(0);  
        else  
            primDig = new Integer(11 - (soma % 11));  
        soma = 0;  
        peso = 11;  
        for (int i = 0; i < num.length(); i++)  
                soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;  
        soma += primDig.intValue() * 2;  
        if (soma % 11 == 0 | soma % 11 == 1)  
            segDig = new Integer(0);  
        else  
            segDig = new Integer(11 - (soma % 11));  
        return primDig.toString() + segDig.toString();  
    }
    public static String geraCPF() {  
        String iniciais = "";  
        Integer numero;  
        for (int i = 0; i < 9; i++) {  
            numero = new Integer((int) (Math.random() * 10));  
            iniciais += numero.toString();  
        }  
        return iniciais + calcDigVerif(iniciais);  
    }  
    public static boolean validaCPF(String cpf) {  
        if (cpf.length() != 11)  
            return false;  
        String numDig = cpf.substring(0, 9);  
        return calcDigVerif(numDig).equals(cpf.substring(9, 11));  
    } 
    
    static public boolean validaCPF2 (String strCpf )
    {
       int     d1, d2;
       int     digito1, digito2, resto;
       int     digitoCPF;
       String  nDigResult;

       d1 = d2 = 0;
       digito1 = digito2 = resto = 0;

       for (int nCount = 1; nCount < strCpf.length() -1; nCount++)
       {
          digitoCPF = Integer.valueOf (strCpf.substring(nCount -1, nCount)).intValue();

          //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
          d1 = d1 + ( 11 - nCount ) * digitoCPF;

          //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
          d2 = d2 + ( 12 - nCount ) * digitoCPF;
       };

       //Primeiro resto da divisão por 11.
       resto = (d1 % 11);

       //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
       if (resto < 2)
          digito1 = 0;
       else
          digito1 = 11 - resto;

       d2 += 2 * digito1;

       //Segundo resto da divisão por 11.
       resto = (d2 % 11);

       //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
       if (resto < 2)
          digito2 = 0;
       else
          digito2 = 11 - resto;

       //Digito verificador do CPF que está sendo validado.
       String nDigVerific = strCpf.substring (strCpf.length()-2, strCpf.length());

       //Concatenando o primeiro resto com o segundo.
       nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

       //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
       return nDigVerific.equals(nDigResult);
    }
}
