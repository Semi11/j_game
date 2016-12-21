enum MoveType{
  MOVE0(MoveStrategy.M0), 
  MOVE1(MoveStrategy.M1);

  private MoveStrategy type;

  private MoveType(MoveStrategy type) {
    this.type = type;
  }

  public void update(PosInfo pos_info, int cnt) {
    this.type.move(pos_info, cnt);
  }

  private enum MoveStrategy {
    M0 {      
      void move(PosInfo pos_info, int cnt) {
        pos_info.setVel(pos_info.getSpe(), pos_info.getDir());
        pos_info.update();
      }
    }
    , 
      M1 {
      void move(PosInfo pos_info, int cnt) {
        pos_info.setVel(2.0, 90);
        pos_info.update();
      }
    };

    abstract void move(PosInfo pos_info, int cnt);
  }
}