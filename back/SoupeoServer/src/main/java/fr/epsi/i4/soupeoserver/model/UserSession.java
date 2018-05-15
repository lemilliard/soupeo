package fr.epsi.i4.soupeoserver.model;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.ArrayList;
import java.util.Date;

@Entity
public class UserSession {
    @Id
    private int _id;
    private String id_terminal;

    private String location;
    private Date start_session;
    private Date end_session;

    @Embedded
    private ArrayList<Parcours> parcours;

    @Embedded
    private ArrayList<IndicePb> indice_pb;

    @Embedded
    private ArrayList<Mood> mood;
}
