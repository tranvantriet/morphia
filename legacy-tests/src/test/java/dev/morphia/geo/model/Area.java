package dev.morphia.geo.model;

import com.mongodb.client.model.geojson.Polygon;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Indexed;
import dev.morphia.utils.IndexDirection;
import org.bson.types.ObjectId;

@Entity
public final class Area {
    @Id
    private ObjectId id;
    private String name;

    @Indexed(IndexDirection.GEO2DSPHERE)
    private Polygon area;

    @SuppressWarnings("UnusedDeclaration")
        // Used by Morphia
    Area() {
    }

    public Area(String name, Polygon area) {
        this.name = name;
        this.area = area;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (area != null ? area.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Area area1 = (Area) o;

        if (area != null ? !area.equals(area1.area) : area1.area != null) {
            return false;
        }
        return name != null ? name.equals(area1.name) : area1.name == null;
    }

    @Override
    public String toString() {
        return "Area{"
               + "name='" + name + '\''
               + ", area=" + area
               + '}';
    }
}
