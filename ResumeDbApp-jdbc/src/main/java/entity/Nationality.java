package entity;

public class Nationality {
    private int id;
    private String countryName;
    private String nationalityName;

    public Nationality() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nationality that = (Nationality) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public Nationality(int id, String countryName, String nationalityName) {
        this.id = id;
        this.countryName = countryName;
        this.nationalityName = nationalityName;
    }

    @Override
    public String toString() {
        return countryName + "(" + nationalityName + ")";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getNationalityName() {
        return nationalityName;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }
}
