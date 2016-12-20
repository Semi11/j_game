class StatusInfo {
  private int hp;
  private int power;

  public StatusInfo(){
    this(10,0);
  }

  public StatusInfo(int hp, int pow){
    setStatus(hp, pow);
  }

  public void setStatus(int hp, int pow) {
    setHp(hp);
    setPower(pow);
  }

  public void setHp(int hp) {
    this.hp=hp;
  }
  
  public void setPower(int pow){
    this.power=pow; 
  }
}