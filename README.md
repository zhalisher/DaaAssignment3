Report 
1. Input Data and Algorithm Results Summary
Test Data Characteristics:

    28 graphs across 4 size categories

    Graph distribution: 5 small, 10 medium, 10 large, 3 x-large

    Vertex ranges: 5-30 (small), 30-300 (medium), 300-1000 (large), 1000-3000 (x-large)

Performance Results Summary:
Graph Category	Algorithm	Avg Time (ms)	Avg Operations	Time Range (ms)
Small	Prim	    0.295	                      626	        0.013 - 0.861
Kruskal	        0.157	                      1,082	      0.013 - 0.422
Medium	Prim	  0.771	                      12,370	    0.059 - 2.509
Kruskal	        1.306	                      62,216	    0.056 - 4.658
Large	Prim	    3.006	                      108,921	    0.499 - 7.922
Kruskal	        8.948	                      430,695	    1.441 - 23.712
X-Large	Prim	  12.251	                    389,873	    3.802 - 24.809
Kruskal	        17,078	                    2,081,167	  10.871 - 21.167

2. Prim's vs Kruskal's: Efficiency Comparison
Theoretical Expectations:

    Prim: O(E log V) - better for dense graphs

    Kruskal: O(E log E) - better for sparse graphs

    Expected: Kruskal should outperform on large sparse graphs

Practical Results:

  Small graphs: Kruskal 47% faster than Prim

  Medium graphs: Prim 41% faster than Kruskal

  Large graphs: Prim 66% faster than Kruskal

  X-Large graphs: Prim 28% faster than Kruskal

Operation Efficiency:

  Kruskal consistently uses 3-5x more operations than Prim

  Prim shows better operation-to-time ratio across all sizes

  
3. Conclusions and Recommendations
Prim's Algorithm is Preferable When:

    Working with medium to large graphs (30+ vertices)

    Memory efficiency is important

    Real-time performance required

    Java environment with PriorityQueue optimization

Kruskal's Algorithm is Preferable When:

  Very small graphs (5-30 vertices)

  Edges are pre-sorted

  Implementation simplicity is priority

  Parallel processing available for sorting

For City Transportation Networks:

Recommendation: Prim's Algorithm

  Most real city networks are medium to large

  Better performance on graphs with 30+ districts

  More consistent execution times

  4 References :
  https://www.youtube.com/watch?v=JptKmWQSerU&t=262s krusakal
  https://www.youtube.com/watch?v=gUHFHy0WXcM&t=167s prim
  google gemini for json input

  https://www.geeksforgeeks.org/dsa/prims-minimum-spanning-tree-mst-greedy-algo-5/ for more info

