DROP TABLE IF EXISTS worker;
create table worker  (
    id bigint not null,
    email varchar(255),
    image_url varchar(255),
    name varchar(255),
    video_url varchar(255),
    created_at timestamp,
    updated_at timestamp,
    primary key (id)
);

