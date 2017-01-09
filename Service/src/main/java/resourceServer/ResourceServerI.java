package resourceServer;

import resources.TestResource;

public interface ResourceServerI {
    int getAge();
    String getName();
    void setResource(TestResource resource);
    TestResource getResource();
}
