package pl.radomczak.repository;

import pl.radomczak.model.Build;
import java.util.Collection;
import java.util.Optional;

public class BuildsRepository {
        private final Collection<Build> builds;

    public BuildsRepository(Collection<Build> builds) {
        this.builds = builds;
    }

    public void addBuild(Build build)
    {
        builds.add(build);
    }

    public Collection<Build> getBuilds()
    {
        return builds;
    }

    public Optional<Build> findByName(String name) {
        for (Build build : builds) {
            if (build.getName().equals(name))
                return Optional.of(build);
        }
        return Optional.empty();
    }
}
