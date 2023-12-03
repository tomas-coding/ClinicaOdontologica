package com.clinicaodontologica.Clinica.Odontologica.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TurnoDTO {
    private Long id;
    private LocalDate fechaTurno;
    private Long pacienteId;
    private Long odontologoId;

    public TurnoDTO(LocalDate fechaTurno, Long pacienteId, Long odontologoId) {
        this.fechaTurno = fechaTurno;
        this.pacienteId = pacienteId;
        this.odontologoId = odontologoId;
    }

    public TurnoDTO() {
    }

    public TurnoDTO(Long id, LocalDate fechaTurno, Long pacienteId, Long odontologoId) {
        this.id = id;
        this.fechaTurno = fechaTurno;
        this.pacienteId = pacienteId;
        this.odontologoId = odontologoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(LocalDate fechaTurno) {
        this.fechaTurno = fechaTurno;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        this.odontologoId = odontologoId;
    }
}
