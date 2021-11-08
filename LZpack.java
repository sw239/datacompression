//Name : Lan Niu ID:1320386
//Name Shizhen Wang ID:1240171
import java.io.*;
import java.util.*;
import java.lang.Math;

class LZpack{
    public static void main(String args []){
        BufferedInputStream bis = new BufferedInputStream(System.in);
        ArrayList <Byte> ba = new ArrayList<Byte>();
        try{
            int i;  
            byte b;  
            int index = 0;  
            int pid = 0, ErNo = 0;   
            int NOP = 0;  
            int ln = 0;  
            int trienum = 0;
            while((i = bis.read()) != -1){
                b = (byte)i;
                //store all data into the array list
                ba.add(b);
                if(i == 10){
                    i = bis.read();
                    if(i == 10){
                        ba.add((byte)i);
                        pid = GetNum(ba);

                        if(trienum < 2){
                            ln = 1;
                        }else{
                            ln = Cal(trienum) + 1;
                        }


                        if(NOP == 0){
                            index |= pid;
                            NOP += ln;
                            
                        }else if((ln + NOP) > 31){
                            wout(index, 2);
                            NOP -= 16;
                            index = index >> 16;
                           
                            if((ln + NOP) > 31){
                                
                                wout(index, 1);
                                NOP -= 8;
                                index >>= 8;
                                pid <<= NOP;
                                index |= pid;
                                NOP += ln;
                            }else{
                                pid = pid << NOP;
                                index |= pid;
                                NOP += ln;
                            }
                           
                            
                        }else{
                            pid = pid << NOP;
                            index |= pid;
                            NOP += ln;
                            
                        }
                        ErNo = GetChar(ba);
                        if((8 + NOP) > 31){
                            wout(index, 2);
                            NOP -= 16;
                            index = index >> 16;
                            ErNo = ErNo << NOP;
                            index |= ErNo;
                            NOP += 8;
                            
                        }else{
                            ErNo = ErNo << NOP;
                            index |= ErNo;
                            NOP += 8;
                         
                        }
                        trienum++;
                        ba.clear();
                    }else if(i == -1){   //next data after \n is the end of the file or data, break the loop
                        
                        break;
                    }else{   
                        pid = GetNum(ba);
                       
                        if(trienum < 2){
                            ln = 1;
                        }else{
                            ln = Cal(trienum) + 1;
                        }
                        if(NOP == 0){
                            index |= pid;
                            NOP += ln;
                            
                        }else if((ln + NOP) > 31){
                            wout(index, 2);
                            NOP -= 16;
                            index = index >> 16;
                            
                            if((ln + NOP) > 31){
                               
                                wout(index, 1);
                                NOP -= 8;
                                index >>= 8;
                                pid <<= NOP;
                                index |= pid;
                                NOP += ln;
                            }else{
                                pid = pid << NOP;
                                index |= pid;
                                NOP += ln;
                            }
                           
                            
                        }else{
                            pid = pid << NOP;
                            index |= pid;
                            NOP += ln;
                            
                        }
                        ErNo = GetChar(ba);
                        
                        if((8 + NOP) > 31){
                            wout(index, 2);
                            NOP -= 16;
                            index = index >> 16;
                            ErNo = ErNo << NOP;
                            index |= ErNo;
                            NOP += 8;
                            
                        }else{
                            ErNo = ErNo << NOP;
                            index |= ErNo;
                            NOP += 8;
                            
                        }
                        trienum++;
                        ba.clear();
                        ba.add((byte)i);
                     
                    }
                }
            }
            if(ba.size() != 0){
               
                if(ba.get(ba.size() - 2) >= 48 && ba.get(ba.size() - 2) <= 57){
                    pid = 0;
                    int len = ba.size();
                    int a = 2;
                    for(int x=0; x<ba.size()-1; x++){
                        if(len > 0){
                            
                            pid += Integer.parseInt(String.valueOf((char)ba.get(x).byteValue())) * Math.pow(10, ba.size() - a);
                            a++;
                            len--;
                        }
                    }
                   
                    if((ln + NOP) > 31){
                        wout(index, 2);
                        NOP -= 16;
                        index = index >> 16;
                        pid = pid << NOP;
                        index |= pid;
                        NOP += ln;
                        
                    }else{
                        pid = pid << NOP;
                        index |= pid;
                        NOP += ln;
                        
                    }
                }else{
                    pid = GetNum(ba);
                    
                    if((ln + NOP) > 31){
                        wout(index, 2);
                        NOP -= 16;
                        index = index >> 16;
                        pid = pid << NOP;
                        index |= pid;
                        NOP += ln;
                    }else{
                        pid = pid << NOP;
                        index |= pid;
                        NOP += ln;
                    }
                    ErNo = GetChar(ba);
                    
                    if((8 + NOP) > 31){
                        wout(index, 2);
                        NOP -= 16;
                        index = index >> 16;
                        ErNo = ErNo << NOP;
                        index |= ErNo;
                        NOP += 8;
                    }else{
                        ErNo = ErNo << NOP;
                        index |= ErNo;
                        NOP += 8;
                        
                    }
                }
            }
            while(NOP > 0){
                wout(index, 2);
                NOP -= 16;
                index = index >> 16;
            }
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private static int GetNum(ArrayList <Byte> ar){
        int pkbit = 0;
        int len = ar.size() - 2;
        int a = 3;
        for(int x=0; x<ar.size() - 2; x++){
            if(len > 0){
                pkbit += Integer.parseInt(String.valueOf((char)ar.get(x).byteValue())) * Math.pow(10, ar.size() - a);
                a++;
                len--;
            }
        }
        return pkbit;
    }
    private static int GetChar(ArrayList <Byte> ar){
        return (int)ar.get(ar.size() - 2).byteValue();
    }
    private static int shiftNum(int num){
        int len = 0;
        if(num == 0){
            return len = 1;
        }
        while(num != 0){
            num /= 2;
            len++;
        }
        return len;
    }
    private static int Cal(int num){
        return (int)(Math.log(num) / Math.log(2));
    }
    
    //write the result
    private static void wout(int text, int times) throws Exception{
        int index = 0;
        byte [] array = new byte[times];
        while(index < times){
            array[index] = (byte)text;
            text = text >> 8;
            index++;
        }
        System.out.write(array);
    }
}
