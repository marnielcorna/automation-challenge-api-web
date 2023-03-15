package Resources;

public enum APIResources {
    PathForFindPets("/v2/pet/findByStatus"),
    PathForPostPet("/v2/pet"),
    DeletePet("/");

    private String resource;

    APIResources(String resource) {

        this.resource=resource;
    }

    public String getResource() {
        return resource;
    }
}
