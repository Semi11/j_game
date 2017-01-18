class StatusInfo {
    private int hp;
    private int power;

    public StatusInfo(){
	this(1,0);
    }

    public StatusInfo(int hp, int pow){
	setStatus(hp, pow);
    }

    public void setStatus(int hp, int pow) {
	setHp(hp);
	setPower(pow);
    }

    public void setHp(int hp) {
	if(hp<0)hp=1;
	this.hp=hp;
    }
  
    public void setPower(int pow){
	this.power=pow; 
    }


    public int getHp() {
	return this.hp;
    }

    public int getPower() {
	return this.power;
    }  

    public void damage(int power){
	if(this.hp>0){
	    this.hp -= power;
	}
    }

}
