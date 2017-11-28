# Sean Gatewood (stg2bd)

import pygame
import gamebox
import random

windowWidth = 800
windowHeight = 600
camera = gamebox.Camera(windowWidth,windowHeight)
counter = 0
z = 0
o = 0
score = 0
lose = 2
colors = ["red","orange","yellow","green","blue","purple","white","cyan","gray"]

#logo=gamebox.from_image(0,0,"http://www.pygame.org/docs/_static/pygame_tiny.png")
player = gamebox.from_color(50,100,"red",20,20)
player.yspeed = 1

walls = []

def moveWalls(t,m,b):
    while t.touches(m):
        m.y += 10
    while t.touches(b) or m.touches(b):
        #print('contact')
        b.y += 10
    camera.draw(t)
    camera.draw(b)
    camera.draw(m)

def makeWall(x):
    global colors
    topLength = random.randint(0,800)
    bottomLength = 1200
    middleLength = 200
    t = gamebox.from_color(x,0,colors[random.randint(0,len(colors)-1)],45,topLength)
    m = gamebox.from_color(x,topLength/2,"black",45,middleLength)
    b = gamebox.from_color(x,topLength/2,colors[random.randint(0,len(colors)-1)],45,bottomLength)
    moveWalls(t,m,b)
    return [t,m,b]


#t,m,b = makeWall(50)
i = 400
while i < windowWidth+800:
    walls.append(makeWall(i))
    i += 400
#print(walls)

def tick(keys):
    global counter,z,o,score,lose
    camera.clear("black")

    if lose == 0:
        if pygame.K_SPACE in keys and z == 0:
            z = 1
            player.yspeed = -20
            #player.yspeed
            player.color = "green"
        elif pygame.K_SPACE not in keys:
            z = 0

        player.yspeed += 2
        player.y = player.y + player.yspeed

        for wall in walls:
            if wall[0].x <= 0:
                wall[0],wall[1],wall[2] = makeWall(windowWidth+400)
                #print('new!')
                continue
            for i in wall:
                i.x -= 10
                camera.draw(i)

            if player.touches(wall[0]) or player.touches(wall[2]):
                lose = 1

            if (counter-37) % 40 == 0:
                score += 1

            if player.y <= 0 or player.y >= windowHeight:
                lose = 1

        #print(z)
            #print(len(walls))

        camera.draw("Score: %r" %(score//3),"Arial",75,"green",650,50)
        counter += 1
        camera.draw(player)
        player.color = "red"
    elif lose == 2:
        camera.draw("Welcome to Flappy Bird!","Arial",75,"green",375,150)
        camera.draw("(Use the spacebar to flap your wings!)","Arial",50,"green",375,220)
        camera.draw("<Press Space to Start>","Arial",50,"green",375,290)
        if pygame.K_SPACE in keys:
            lose = 0
    else:
        camera.draw("You lose!","Arial",75,"green",350,150)
        camera.draw("Score: %r" %(score//3),"Arial",75,"green",350,220)
        camera.draw("<Press X to Exit>","Arial",75,"green",350,290)
        if pygame.K_x in keys:
            exit()
    camera.display()

gamebox.timer_loop(30,tick)
