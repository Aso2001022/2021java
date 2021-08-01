package pkgsaisyu;
import java.util.*;
public class Game {
    static final int mapH = 5;
    static final int mapW = 5;
    static boolean map[][] = new boolean[mapH][mapW];
    static Ship ships[] = new Ship[3];
    static int turncount = 1;
    static int dropx;
    static int dropy;

    public static void dispT(){
        System.out.println("***********************");
        System.out.println("       戦艦ゲーム !!      ");
        System.out.println("***********************");
    }
  public static void disEnd(){
    System.out.println("***********************");
    System.out.println("      ゲームクリア  !!!     ");
    System.out.println("***********************");
}
    public static void init(){
        
        Random rd = new Random();
        int count = 0;
        while(count < 3){
            int x = rd.nextInt(mapH);
            int y = rd.nextInt(mapW);
            if(!map[x][y]){
                map[x][y] = true;
                ships[count] = new Ship(count+1,x,y);
                count++;
            }
        }
    }
    public static void move(Ship ship){
        Random rd = new Random();
        while(true){
            int nx = rd.nextInt(mapH);
            int ny = rd.nextInt(mapW);
            if(!map[nx][ny]){
                map[nx][ny] = true;
                ship.setX(nx);
                ship.setY(ny);
                break;
            }
        }
    }
     public static void disTrun(){
        System.out.printf("-----[ターン%d]-----\n",turncount);
        for(Ship ship : ships){
            ship.disViability();
        }
        turncount++;
    }
    public static void input(){
        Scanner sc = new Scanner(System.in);
        try{

            System.out.printf("爆弾のX座標を入力してください(1-%d)\n",mapH);
            dropx = sc.nextInt()-1;
            System.out.printf("爆弾のY座標を入力してください(1-%d)\n",mapW);
            dropy = sc.nextInt()-1;

            if(dropx<0 || dropy<0 || dropx>=mapH || dropy>=mapW){
                System.out.println("指定された範囲内の数値を入力されてください");
                input();
            }

        }catch(Exception e){
            System.out.println("数値を入力してください");
            input();
        }
        sc.close();
        
    }

    //爆弾を落とす
    public static void dropbomb(){
        for(Ship ship : ships){
            if(ship.gethp()>0){
                
                int cheak = ship.damage(dropx,dropy);
                
                if(cheak == 0){
                    System.out.printf("%s : はずれ！\n",ship.getname());
                }else if(cheak == 1){
                    System.out.printf("%s : 波高し！\n",ship.getname());
                }else if(cheak == 2){
                    System.out.printf("%s : 当たった！\n",ship.getname());

                    map[ship.getx()][ship.gety()] = false;
                    move(ship);

                }else{
                    System.out.printf("%s : 撃沈！\n",ship.getname());
                    
                    
                    map[ship.getx()][ship.gety()] = false;
                }
            }else{
                System.out.printf("%s : 撃沈済み\n",ship.getname());
            }
            
        }
    }

    public static boolean proceed(){
        int living_ship = 0;

        for(Ship ship : ships){
            if(ship.gethp()>0){
                living_ship++;
            }
        }
        if(living_ship>0){
            return true;
        }else{
            return false;
        }
    }
}

