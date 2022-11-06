package sample.services;

import sample.models.DTOs.RegisterDTO;

public interface RegisterService {
    boolean registerAndLogin(RegisterDTO registerDTO);
}
