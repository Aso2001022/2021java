package pkgsaisyu;

public class Ship {
    private String name;
    private int x;
    private int y;
    private int hp = 3;

    private static final int NO_HIT=0;
    private static final int HEAR= 1;
    private static final int HIT = 2;
    private static final int SINK = 3;

    public String getname(){
        return name;
    }

    public int getx(){
        return x;
    }

    public int gety(){
        return y;
    }

    public int gethp(){
        return hp;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }
    public Ship(int shipNumber, int x, int y){
        this.name = "船"+Integer.toString(shipNumber);
        this.x = x;
        this.y = y;
    }
    public void disViability(){
        if(hp>0){
            System.out.printf("%s : 生きてる\n",name);
        }else{
            System.out.printf("%s : 撃沈済み\n",name);
        }
    }

    //爆弾による影響
    public int damage(int x,int y){
        //爆弾と船の距離
        int kyori = Math.abs(this.x-x) + Math.abs(this.y-y);
        if(kyori == 0){//当たった時
            hp--;//HPを１減らす
            if(hp>0){
                return HIT;
            }else{
                return SINK;
            }
        }else if(kyori == 1){//近くに落ちた時
            return HEAR;
        }else{//それ以外
            return NO_HIT;
        }
    }
}

