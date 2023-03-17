package coursework.socksonlinestoreapp.model;

public enum Size {
    XXS(28), XS(31), S(35), M(39), L(43), XL(47), XXL(50);
    private final int numSize;

    Size(int numSize) {
        this.numSize = numSize;
    }
}
