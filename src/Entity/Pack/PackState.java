package Entity.Pack;

public enum PackState {
    PACK_INSERTED(0),
    PACK_DELIVERING(1),
    PACK_DELIVERED(2),
    NOT_A_NUMBER(-1);

    private  int number;

    PackState(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public PackState findByInt(int choice){
        switch (choice) {
            case 0 -> {
                return PACK_INSERTED;
            }
            case 1 -> {
                return PACK_DELIVERING;
            }
            case 2 -> {
                return PACK_DELIVERED;
            }
        }
        return  NOT_A_NUMBER;
    }
}
