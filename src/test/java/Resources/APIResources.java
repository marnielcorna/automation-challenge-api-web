package Resources;

public enum APIResources {
    PathForFindPets("/v2/pet/findByStatus"),
    PathForPostPet("/v2/pet"),
    PathForUpdateAPet("/v2/pet"),
    PathToDeletePet("/");

    private String resource;

    APIResources(String resource) {

        this.resource=resource;
    }

    public String getResource() {
        return resource;
    }
}
