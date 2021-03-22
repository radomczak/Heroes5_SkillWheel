package pl.radomczak.repository;

import pl.radomczak.model.Build;

import java.util.Map;
import java.util.Optional;

public class BuildsRepository {
    private final Map<String, Build> builds;

    public BuildsRepository(Map<String, Build> builds) {
        this.builds = builds;
    }

    public void addBuild(String name, Build build)
    {
        builds.put(name,build);
    }
    public void removeBuild(String name)
    {
        builds.remove(name);
    }

    public Map<String, Build> getBuilds() {
        return builds;
    }

    public Optional<Build> findByName(String name) {
        if (builds.containsKey(name))
            return Optional.of(builds.get(name));
        return Optional.empty();
    }
}
