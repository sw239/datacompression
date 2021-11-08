//Name : Lan Niu ID:1320386
//Name Shizhen Wang ID:1240171
import java.io.BufferedInputStream;
import java.lang.Math;
import java.util.*;

class LZunpack{
    public static void main(String args[]){
        try{
            BufferedInputStream bis = new BufferedInputStream(System.in);
            int i, Out, ch = 0;
            int lNO = 0;
            int TNO = 0;
            int PNO, ERR;
            int bits = 0;
            while((i = bis.read()) != -1){
                if(lNO != 0){
                    i = ch | ((i & 255) << lNO);
                }else{
                    i = i & 255;
                }
                lNO += 8;
                if(TNO < 2){
                    PNO = i & 1;
                    lNO -= 1;
                    if(lNO < 8){
                        ch = bis.read();
                        if(ch == -1){
                            break;
                        }else{
                            i >>= 1;
                            i |= (ch << 7);
                            lNO += 8;
                        }
                    }else{
                        i >>= 1;
                    }
                    ERR = i & 255;
                    ch = (i >> 8);
                    lNO -= 8;
		    if((byte)ERR==0)
			{
			    System.out.print(PNO);System.out.write('\n');
			}
		    else{
			System.out.print(PNO);
			System.out.write((byte)ERR);
			System.out.write('\n');
		    }
                    TNO++;
                }
		else{
                    bits = Cal(TNO) + 1;
                    if(lNO < bits){
                        ch = bis.read();
                        if(ch == -1){
                            break;
                        }else{
                            ch <<= lNO;
                            i |= ch;
                            lNO += 8;
                        }
                    }
                    PNO = i & getNO(bits);
                    lNO -= bits;
                    if(lNO < 8){
                        ch = bis.read();
                        if(ch == -1){
                            break;
                        }else{
                            i >>= bits;
                            i |= ch << lNO;
                            lNO += 8;
                        }
                    }else{
                        i >>= bits;
                    }
                    ERR = i & 255;
                    ch = (i >> 8);
                    lNO -= 8;
		    if((byte)ERR==0)
			{
			    System.out.print(PNO);System.out.write('\n');
			}else{
			System.out.print(PNO);
			System.out.write((byte)ERR);
			System.out.write('\n');
		    }
                    TNO++;
                   
                }
            }
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static int getNO(int bits){
        int Out = 0;
        for(int i=0; i<bits; i++){
            Out += Math.pow(2, i);
        }
        return Out;
    }
    
    private static int Cal(int num){
        return (int)(Math.log(num) / Math.log(2));
    }
}
