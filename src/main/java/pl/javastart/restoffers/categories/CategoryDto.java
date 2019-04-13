package pl.javastart.restoffers.categories;

public class CategoryDto {
    private int offers;
    private String name;
    private String description;

    public CategoryDto(int offers, String name, String description) {
        this.offers = offers;
        this.name = name;
        this.description = description;
    }

    public CategoryDto() {
    }

    public int getOffers() {
        return offers;
    }

    public void setOffers(int offers) {
        this.offers = offers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
