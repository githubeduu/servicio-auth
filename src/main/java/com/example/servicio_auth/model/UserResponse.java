package com.example.servicio_auth.model;

public class UserResponse {
    private Long id;
    private String nombre;
    private String rut;
    private String direccion;
    private String comuna;
    private int rolId;
    private Role roles; // Cambia List<Role> a Role

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public Role getRoles() { // Cambia el getter para que devuelva Role
        return roles;
    }

    public void setRoles(Role roles) { // Cambia el setter para aceptar un objeto Role
        this.roles = roles;
    }

    public static class Role {
        private int id;
        private String rol;

        // Getters y setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRol() {
            return rol;
        }

        public void setRol(String rol) {
            this.rol = rol;
        }
    }
}
