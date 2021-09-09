INSERT INTO public.user(
    login, password)
VALUES ('John', '81dc9bdb52d04dc20036dbd8313ed055');

INSERT INTO public.user(
    login, password)
VALUES ('Mike', '81dc9bdb52d04dc20036dbd8313ed055');

INSERT INTO public.user(
    login, password)
VALUES ('Kyle', '81dc9bdb52d04dc20036dbd8313ed055');

INSERT INTO public.user(
    login, password)
VALUES ('Mark', '81dc9bdb52d04dc20036dbd8313ed055');

INSERT INTO public.user(
    login, password)
VALUES ('Robert', '81dc9bdb52d04dc20036dbd8313ed055');


INSERT INTO public.chatroom(
    name, owner)
VALUES ('chatroom1', 1);

INSERT INTO public.chatroom(
    name, owner)
VALUES ('chatroom2', 1);

INSERT INTO public.chatroom(
    name, owner)
VALUES ('chatroom3', 2);

INSERT INTO public.chatroom(
    name, owner)
VALUES ('chatroom4', 2);

INSERT INTO public.chatroom(
    name, owner)
VALUES ('chatroom5', 2);


INSERT INTO public.message(
    author, chatroom, text)
VALUES (1, 1, 'Im user1');

INSERT INTO public.message(
    author, chatroom, text)
VALUES (2, 1, 'Im user2');

INSERT INTO public.message(
    author, chatroom, text)
VALUES (3, 1, 'Im user3');

INSERT INTO public.message(
    author, chatroom, text)
VALUES (4, 1, 'Im user4');

INSERT INTO public.message(
    author, chatroom, text)
VALUES (5, 1, 'Im user5');