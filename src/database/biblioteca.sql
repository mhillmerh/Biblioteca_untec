-- Crear base de datos
create database if not exists biblioteca;

use biblioteca;

-- Creación de tablas

create table usuarios
(
    id       int auto_increment primary key not null,
    nombre   varchar(100) not null,
    email    varchar(100) not null,
    password varchar(50)  not null,
    rol varchar(50) not null
);

create table libros (
    id int auto_increment primary key not null,
    titulo varchar(100) not null,
    autor varchar(50) not null,
    disponible boolean default true
);

create table prestamos (
    id int auto_increment primary key not null,
    usuario_id int not null,
    libro_id int not null,
    fecha_prestamo date,
    fecha_devolucion date,
    foreign key (usuario_id) references usuarios(id),
    foreign key (libro_id) references  libros(id)
);

insert into usuarios (nombre, email, password, rol)
values
    ('Administrador', 'admin@biblioteca.com', 'admin123456', "Administrador"),
    ('Usuario', 'usuario@gmail.com', 'user123456', "Usuario");

insert into libros (titulo, autor, disponible)
values
    ('Cien años de soledad', 'Gabriel Garcia Marquez', true),
    ('Don Quijote de la Mancha', 'Miguel de Cervantes', true),
    ('1984', 'George Orwell', true),
    ('El principito', 'Antoine de Saint Exupery', true),
    ('La Metamorfosis', 'Frank Kafka', true),
    ('Fahrenheit 451', 'Ray Bradbury', true),
    ('Crimen y castigo', 'Fiodor Dostoyevski', false),
    ('Dune', 'Frank Herbert', false),
    ('El señor de los anillos. La comunidad del anillo', 'J.R.R. Tolkien', false),
    ('Honor de Lobo', 'Lee Lightner', false);

insert into prestamos (usuario_id, libro_id, fecha_prestamo, fecha_devolucion)
values
    (2, 1, '2026-02-20', '2026-02-27'),
    (2, 2, '2026-02-21', '2026-02-28'),
    (1, 3, '2026-02-22', '2026-03-01'),
    (2, 4, '2026-02-23', '2026-03-02'),
    (1, 5, '2026-02-24', '2026-03-03'),
    (2, 6, '2026-02-25', '2026-03-04'),
    (2, 7, '2026-02-26', null),
    (1, 8, '2026-02-27', null),
    (2, 9, '2026-03-01', null),
    (1, 10, '2026-03-02',null);
