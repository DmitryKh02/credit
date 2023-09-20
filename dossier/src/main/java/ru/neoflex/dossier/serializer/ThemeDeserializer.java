package ru.neoflex.dossier.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import lombok.extern.slf4j.Slf4j;
import ru.neoflex.dossier.enums.Theme;
import ru.neoflex.dossier.exception.NoSuchThemeException;

import java.io.IOException;

@Slf4j
public class ThemeDeserializer extends JsonDeserializer<Theme> {
    @Override
    public Theme deserialize(JsonParser p, DeserializationContext text) throws IOException {
        TextNode node = p.getCodec().readTree(p);
        String value = node.asText();

        try {
            return Theme.fromString(value);
        }
        catch (NoSuchThemeException ex){
            log.error("ThemeDeserializer.deserialize - {}", ex.getError());
            throw new IOException("Invalid input field theme: " + value);
        }
    }
}
