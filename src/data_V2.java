import org.apache.xmlbeans.impl.xb.xsdschema.Public;

public class data_V2 {
    public int id;
    public String sitename;
    public String Postname;
    public String description;
    public String DateDePublication;
    public String Entreprise;
    public String EntrepriseDes;
    public String region;
    public String Competence;
    public String Type_de_contrat;
    public String secteur;
    public String experience;
    public String niveau_etude;
    public String langue;
    int nombre_de_poste;
    public String Postuler;

    public data_V2(int id,String sitename, String Postname,
                   String description, String DateDePublication, String Entreprise,String Entreprisedes, String region, String Competence, String Type_de_contrat, String secteur
            ,String experience, String niveau_etude , String langue, int nombre_de_poste,String Postuler) {
        this.id = id;
        this.sitename =sitename;
        this.Postname = Postname;
        this.DateDePublication = DateDePublication;
        this.Entreprise = Entreprise;
        this.EntrepriseDes = Entreprisedes;
        this.description = description;
        this.region = region;
        this.Competence = Competence;
        this.Type_de_contrat = Type_de_contrat;
        this.secteur = secteur;
        this.experience = experience;
        this.niveau_etude = niveau_etude;
        this.langue = langue;
        this.nombre_de_poste = nombre_de_poste;
        this.Postuler = Postuler;
    }
}
