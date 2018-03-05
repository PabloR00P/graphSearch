package graphsearch;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.imageio.ImageIO;
public class labyrinth {
    int [][] mirrorArray;
    private final ArrayList<Integer> tempPixels;
    private final int[][] finalArray, factorArray;
    private final int size;
    private int [][] pixeles;
    private int divisor;
    private static final int IMG_WIDTH = 600;
    private static final int IMG_HEIGHT = 600;
    
    BufferedImage bufferedImage;
        
    public labyrinth() {
        size = 100;
        this.factorArray = new int[size][size];
        this.mirrorArray = new int[size][size];
        this.finalArray = new int[size][size];
        this.tempPixels = new ArrayList<>();
    }
    
    public void start() throws IOException{
        
        final File file = new File("/Users/josepablorodriguez/Desktop/6.png");
        System.out.println(file);
        BufferedImage image = ImageIO.read(file);
        
        int height = image.getHeight(),
            width = image.getWidth(),
            a=0,
            b=0,
            row=0,
            column=0,
            counterX=0,
            counterY=0;
        
        if(height != 600 || width != 600){
            try{
                BufferedImage originalImage = ImageIO.read(file);
                System.out.println(originalImage.getType());
                int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();         
                image = resize(originalImage, type);
                height= image.getHeight(); //resize de height con valores que hemos definido
                width = image.getWidth(); //resize de width con valores que hemos definido
            } 
            catch(Exception e){  
            }      
        }
  
        divisor = (int)(height/size)+1;
      
        // aqui se guardan todos los pixeles segun el ato y ancho de la imagen
        pixeles = new int[height][width];
        
        
        // Se llena la matriz de pixeles con los colores que vaya encontrando.
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {   
                pixeles[counterY][counterX]=image.getRGB(x,y);
                counterY++;
            }
            counterY=0;
            counterX++; 
        }
        
        bufferedImage = new BufferedImage(pixeles.length, pixeles[0].length,
                                      BufferedImage.TYPE_INT_RGB);
        
//dependiento del divisor aqui se agregan los pixeles comunes a la la lista de finalArray 
        for(int i =0; i<height;i++){
            for(int j =0; j<width;j++){
                tempPixels.add(pixeles[i][j]);
                if(j%divisor == 0 && j!=0){
                    int result = calcPixels(tempPixels);
                    finalArray[a][b]=result;
                    b+=1;
                    tempPixels.clear();
                }
            }
            b=0;
            if(i%divisor == 0 && i!=0){
                a+=1;
            }
        }
       
        for (int vertical = 0; vertical < height; vertical++) {
            for (int horizontal = 0; horizontal < width; horizontal++) {
                bufferedImage.setRGB(vertical, horizontal, finalArray[row][column]);
                if(horizontal%divisor == 0 & horizontal!=0){
                    column+=1;
                }         
            }
            column=0;
            if(vertical%divisor == 0 && vertical!=0){
                row+=1;
            }
        }
        // con esta funcion llenamos la matriz factorArray con numeros segun los colores que se han encontrado
        findColors();
       
        File discretizedImage = new File("/Users/josepablorodriguez/Desktop/discretizedImage.png");
        ImageIO.write(bufferedImage, "png", discretizedImage);
      
        // aqui se crea  un problema a resolver con estado inicial, acciones y el goal
        Solver factory = new Solver();
        
        //aqui se hace una copia de lo que habia en factoryArray que tiene ya los colores en las listas
        copy();
        
        InterfaceSolver problema = factory.getSolver("bfs",factorArray,new Block(getStart()),new Block(getFinish()),size);
        ArrayList<Block> result = problema.graphSearch();
       
        Problem p = new Problem(size,mirrorArray,result);
        p.resolve();

    }
    
    public void copy(){
        
        for(int i=0; i<factorArray.length;i++){
            for(int j=0; j<factorArray.length;j++){
               this.mirrorArray[i][j]=factorArray[i][j];
            }
        }     
    }
    
    
     public int[] getStart(){
        for(int i=0; i<factorArray.length;i++){
            for(int j=0; j<factorArray.length;j++){
                if(factorArray[i][j]==3){
                    int[] start = {i,j,0};
                    return start;
                }
            }
        }
        return null;
    }
    
    public int[] getFinish(){
        for(int i=0; i<factorArray.length;i++){
            for(int j=0; j<factorArray.length;j++){
                if(factorArray[i][j]==2){
                    int[] finish = {i,j,0};
                    return finish;
                }
            }
        }
        return null;
    }
    
      
    public Integer calcPixels(ArrayList<Integer> lista){
        int masRepetido = common(lista);
        return masRepetido;
    }
    
    // aqui se encuentra el metodo que mas se repite en la lista
    public static <T> T common(List<T> list) {
        Map<T, Integer> map = new HashMap<>();
        for (T t : list) {
            Integer val = map.get(t);
            map.put(t, val == null ? 1 : val + 1);
        }
        Entry<T, Integer> max = null;
        for (Entry<T, Integer> e : map.entrySet()) {
            if (max == null || e.getValue() > max.getValue())
                max = e;
        }
        
        return max.getKey();  
    }
    
    private static BufferedImage resize(BufferedImage originalImage, int type){
	BufferedImage resize = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
    	Graphics2D g = resize.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();
	return resize;
    }  
    
    
    public void findColors(){
        for(int i=0; i< finalArray.length;i++){
            for(int j=0; j<finalArray.length;j++){
                float hsb[] = new float[3];
                int color = finalArray[i][j];
                int r = (color >> 16) & 0xFF;
                int g = (color >>  8) & 0xFF;
                int b = color & 0xFF;
                Color.RGBtoHSB(r, g, b, hsb);
                  
                if(hsb[1] < 0.1 && hsb[2] > 0.9){
                    // nearly white
                    factorArray[j][i] = 1;
                }         
                else if(hsb[2] < 0.1){
                    // nearly black
                     factorArray[j][i] = 0;
                }
                else{
                    float deg = hsb[0]*360;
                    if(deg>=0 && deg<30){
                        if(r>190 && g>190 && b>190) {
                            // aqui se agrega el color negro
                             factorArray[j][i] = 1;
                        }
                        else if(r<85 && g<85 && b<85){
                            // aqui se agrega el color blanco
                            factorArray[j][i] = 0;
                        }
                        else if(r>85 &&r<190 && g>85 && g<190 && b>85 && g<190){
                             factorArray[j][i] = 1;
                        }
                        else{
                            // aqui se agrega el color verde
                            factorArray[j][i] = 3;
                        }
                        
                    }
                    else if (deg >=  90 && deg < 150){
                         // aqui se agrega el color rojo
                         factorArray[j][i] = 2;
                    }
                    else if(deg>=320 && deg<360){
                        // aqui se agrega el color verde
                        factorArray[j][i] = 3;
                    }
                   
                }
            }
        }
    }  
}