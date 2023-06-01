package com.exist.ecc.core.service;

import com.exist.ecc.core.model.Person;
import com.exist.ecc.core.model.Role;
import com.exist.ecc.core.model.RoleDTO;
import com.exist.ecc.core.repository.RoleRepository;
import com.exist.ecc.exception.PersonNotFoundException;
import com.exist.ecc.exception.RoleNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;
    

    @Test
    void testGetRoleById() {
        
        Long roleId = 1L;
        Role role = new Role(roleId, "Admin", new ArrayList<>());
        Mockito.when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));
        
        Optional<Role> result = roleService.getRoleById(roleId);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(role, result.get());
        Mockito.verify(roleRepository, Mockito.times(1)).findById(roleId);
    }

    @Test
    void testCreateRole() {
        Role role = new Role(1L, "Admin", new ArrayList<>());
        Mockito.when(roleRepository.save(role)).thenReturn(role);

        Role result = roleService.createRole(role);

        Assertions.assertEquals(role, result);
        Mockito.verify(roleRepository, Mockito.times(1)).save(role);
    }

    @Test
    void testCreateRoleWithNullRole() {
        Assertions.assertThrows(RoleNotFoundException.class, () -> roleService.createRole(null));
        Mockito.verify(roleRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void testFindRoleById() {
        
        Long roleId = 1L;
        Role role = new Role(roleId, "Admin", new ArrayList<>());
        Mockito.when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        RoleDTO result = roleService.findRoleById(roleId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(role.getId(), result.getId());
        Assertions.assertEquals(role.getRoleName(), result.getRoleName());
        Mockito.verify(roleRepository, Mockito.times(1)).findById(roleId);
    }

    @Test
    void testFindRolesThenConvertToRoleDTO() {
        
        Role role1 = new Role(1L, "Admin", new ArrayList<>());
        Role role2 = new Role(2L, "User", new ArrayList<>());
        List<Role> roles = List.of(role1, role2);
        Mockito.when(roleRepository.findAll()).thenReturn(roles);

        List<RoleDTO> result = roleService.findRolesThenConvertToRoleDTO();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(role1.getId(), result.get(0).getId());
        Assertions.assertEquals(role1.getRoleName(), result.get(0).getRoleName());
        Assertions.assertEquals(role2.getId(), result.get(1).getId());
        Assertions.assertEquals(role2.getRoleName(), result.get(1).getRoleName());
        Mockito.verify(roleRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testUpdateRoleById() {
        
        Long roleId = 1L;
        Role existingRole = new Role(roleId, "Admin", new ArrayList<>());
        Role updatedRole = new Role(roleId, "Super Admin", new ArrayList<>());
        Mockito.when(roleRepository.findById(roleId)).thenReturn(Optional.of(existingRole));
        Mockito.when(roleRepository.save(existingRole)).thenReturn(existingRole);

        RoleDTO result = roleService.updateRoleById(roleId, updatedRole);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(existingRole.getId(), result.getId());
        Assertions.assertEquals(existingRole.getRoleName(), result.getRoleName());
        Mockito.verify(roleRepository, Mockito.times(1)).findById(roleId);
        Mockito.verify(roleRepository, Mockito.times(1)).save(existingRole);
    }

    @Test
    void testUpdateRoleByIdWithNonExistingRole() {
        
        Long roleId = 1L;
        Role updatedRole = new Role(roleId, "Super Admin", new ArrayList<>());
        Mockito.when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        Assertions.assertThrows(RoleNotFoundException.class, () -> roleService.updateRoleById(roleId, updatedRole));
        Mockito.verify(roleRepository, Mockito.times(1)).findById(roleId);
        Mockito.verify(roleRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void testDeleteRoleById() {
        Long roleId = 1L;
        roleService.deleteRoleById(roleId);

        Mockito.verify(roleRepository, Mockito.times(1)).deleteById(roleId);
    }
}
