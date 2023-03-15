package Resources;

public enum APIResources {
    PathForFindPetsByStatus("/v2/pet/findByStatus"),
    PathForPostPet("/v2/pet"),
    PathForUpdateAPet("/v2/pet"),
    PathToDeletePet("/v2/pet/");

    private String resource;

    APIResources(String resource) {

        this.resource=resource;
    }

    public String getResource() {
        return resource;
    }
}
