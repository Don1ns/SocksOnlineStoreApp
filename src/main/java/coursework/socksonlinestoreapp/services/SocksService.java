package coursework.socksonlinestoreapp.services;

import coursework.socksonlinestoreapp.model.Socks;
import coursework.socksonlinestoreapp.model.Color;
import coursework.socksonlinestoreapp.model.Size;

public interface SocksService {
    Socks addSocks(Socks socks, int quantity);

    Socks releaseSocks(Socks socks, int quantity);

    int getQuantity(Color color, Size size, Integer cottonMin, Integer cottonMax);

    boolean writeOffSocks(Socks socks, int quantity);
}
