package coursework.socksonlinestoreapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Socks {
    private Color color;
    private Size size;
    private int cottonPart;

}
