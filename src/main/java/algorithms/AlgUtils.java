package algorithms;

import graph.Arc;

public class AlgUtils {

    public static String getFromId(Arc arc){
        return arc.getFrom().getIdentifier();
    }

    public static String getToId(Arc arc){
        return arc.getTo().getIdentifier();
    }
}
