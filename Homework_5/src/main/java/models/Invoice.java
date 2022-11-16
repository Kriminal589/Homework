package models;

import java.sql.Date;

public record Invoice(int id, int organization_id, Date date) {}