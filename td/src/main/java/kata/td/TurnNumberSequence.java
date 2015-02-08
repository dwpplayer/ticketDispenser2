package kata.td;


import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by benwu on 14-6-10.
 */
public class TurnNumberSequence {
    private static ConcurrentHashMap<String, Integer> mapTurnNumber = new ConcurrentHashMap<String, Integer>();
    static {
        mapTurnNumber.put("vip", 1001);
        mapTurnNumber.put("regular", 2001);
    }

    public int getNextTurnNumber()
    {
        return getNextTurnNumberWithType("");
    }

    public int getNextTurnNumberWithType(String turnNumberType)
    {
        if (!mapTurnNumber.containsKey(turnNumberType))
        {
            synchronized (mapTurnNumber) {
                if (!mapTurnNumber.containsKey(turnNumberType)) {
                    mapTurnNumber.put(turnNumberType, 0);
                }
            }
        }

        int nRetValue = 0;
        synchronized (mapTurnNumber) {
            nRetValue = mapTurnNumber.get(turnNumberType);
            mapTurnNumber.put(turnNumberType, nRetValue + 1);
        }
        return nRetValue;
    }
}