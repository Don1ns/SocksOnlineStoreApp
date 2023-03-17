package coursework.socksonlinestoreapp.services.impl;

import coursework.socksonlinestoreapp.model.Socks;
import coursework.socksonlinestoreapp.model.Color;
import coursework.socksonlinestoreapp.model.Size;
import coursework.socksonlinestoreapp.services.SocksService;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class SocksServiceImpl implements SocksService {
    private final Map<Socks, Integer> socksMap = new HashMap<>();

    @Override
    public Socks addSocks(Socks socks, int quantity) {
        if (!socksMap.containsKey(socks)) {
            socksMap.put(socks, quantity);
        } else {
            int newQuantity = socksMap.get(socks) + quantity;
            socksMap.put(socks, newQuantity);
        }
        return socks;
    }

    @Override
    public Socks releaseSocks(Socks socks, int quantity) {
        if (socksMap.containsKey(socks) && socksMap.get(socks) >= quantity) {
            int newQuantity = socksMap.get(socks) - quantity;
            socksMap.put(socks, newQuantity);
            return socks;
        }
        return null;
    }

    @Override
    public int getQuantity(Color color, Size size, Integer cottonMin, Integer cottonMax) {
        int quantity = 0;
        if (cottonMin == null && cottonMax == null) {
            for (Map.Entry<Socks, Integer> entry : socksMap.entrySet()) {
                if (entry.getKey().getColor() == color && entry.getKey().getSize() == size) {
                    quantity += entry.getValue();
                }
            }
        } else if (cottonMin != null && cottonMax != null) {
            for (Map.Entry<Socks, Integer> entry : socksMap.entrySet()) {
                if (entry.getKey().getColor() == color && entry.getKey().getSize() == size && entry.getKey().getCottonPart() > cottonMin && entry.getKey().getCottonPart() < cottonMax) {
                    quantity += entry.getValue();
                }
            }
        } else if (cottonMin != null) {
            for (Map.Entry<Socks, Integer> entry : socksMap.entrySet()) {
                if (entry.getKey().getColor() == color && entry.getKey().getSize() == size && entry.getKey().getCottonPart() > cottonMin) {
                    quantity += entry.getValue();
                }
            }
        } else {
            for (Map.Entry<Socks, Integer> entry : socksMap.entrySet()) {
                if (entry.getKey().getColor() == color && entry.getKey().getSize() == size && entry.getKey().getCottonPart() < cottonMax) {
                    quantity += entry.getValue();
                }
            }
        }
        return quantity;
    }

    @Override
    public boolean writeOffSocks(Socks socks, int quantity) {
        if (socksMap.containsKey(socks) && socksMap.get(socks) > quantity) {
            int newQuantity = socksMap.get(socks) - quantity;
            socksMap.put(socks, newQuantity);
            return true;
        }
        return false;
    }
}
