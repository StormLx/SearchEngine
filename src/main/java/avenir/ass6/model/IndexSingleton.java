package avenir.ass6.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

public enum IndexSingleton implements Serializable {

    INSTANCE;

    @Getter
    @Setter
    Hashtable<String, ArrayList<String>> hashtable;

    IndexSingleton() {
        this.hashtable = new Hashtable<>();
    }
}
