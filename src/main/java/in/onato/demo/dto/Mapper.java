package in.onato.demo.dto;

import in.onato.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEncryptedPassword(user.getPassword());
        dto.setName(user.getName());
        dto.setAddress(user.getAddress());
        dto.setRole(user.getRole());
        return dto;
    }
}
