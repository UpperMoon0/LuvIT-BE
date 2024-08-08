package com.nhat.structurizebe.models.documents;

import com.nhat.structurizebe.models.BlockProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@NoArgsConstructor
@Document(collection = "blocks")
public class BlockDocument {
    @Id
    private String id;
    private String name;
    private Map<String, String> textures;
}
