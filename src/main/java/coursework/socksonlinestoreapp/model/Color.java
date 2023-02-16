package coursework.socksonlinestoreapp.model;

public enum Color {
    RED("Красный"), BLUE("Синий"), GREEN("Зеленый"),
    WHITE("Белый"), BLACK("Черный"), PURPLE("Фиолетовый"),
    YELLOW("Желтый"), ORANGE("Оранжевый"), PINK("Розовый"),
    GRAY("Серый"), BROWN("Коричневый");
    private final String text;

    Color(String text) {
        this.text = text;
    }
}
