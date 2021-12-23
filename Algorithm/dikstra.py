import heapq
from sys import _current_frames


graph = {
    'A' : {'B':8,'C':2,'D':3},
    'B' : {},
    'C' : {'B':3,'D':2},
    'D' : {'E':1,'F':4},
    'E' : {},
    'F' : {}

}


def dijkstra(graph, first) :
    distance = {node:float('inf') for node in graph}
    distance[first] = 0
    queue = []
    
    #A가 배열[c,b,d] 임으로 queue에 들어가는듯
    heapq.heappush(queue,[distance[first],first])
   
    while queue :
        current_distance, current_node = heapq.heappop(queue)
        if distance[current_node] < current_distance:
            continue
        for next_node, weight in graph[current_node].items():
            print(next_node, weight)
            print(distance[next_node])
            total_distance = current_distance + weight

            if total_distance < distance[next_node]:
                distance[next_node] = total_distance
                heapq.heappush(queue, [total_distance, next_node])

    return distance


print(dijkstra(graph, 'A'))
