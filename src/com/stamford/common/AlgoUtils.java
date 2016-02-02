package com.stamford.common;

import com.stamford.common.datastruc.GraphAdjacencyLists;
import com.stamford.common.datastruc.GraphPath;
import com.stamford.common.datastruc.GraphSCC;
import com.stamford.mst.EdgeMST;
import com.stamford.mst.GraphMST;
import com.stamford.scheduling.JobMultiplication;
import com.stamford.scheduling.JobSubstract;

import java.io.File;
import java.util.*;

/**
 * Created by YaskalVV on 11.07.13.
 */
public class AlgoUtils {
    public static int[] readIntArray(String fileName) {
        final ArrayList arr = new ArrayList();
        /*new File(fileName).invokeMethod("eachLine", new Object[]{new Closure(this, this) {
            public Object doCall(Object it) {
                return arr << Integer.parseInt((String) it);
            }

            public Object doCall() {
                return doCall(null);
            }

        }});*/
        return listToIntArray(arr);
    }

    public static long[] readLongArray(String fileName) {
        final ArrayList arr = new ArrayList();
        /*new File(fileName).invokeMethod("eachLine", new Object[]{new Closure(this, this) {
            public Object doCall(Object it) {
                return arr << Long.parseLong((String) it);
            }

            public Object doCall() {
                return doCall(null);
            }

        }});*/
        return listToLongArray(arr);
    }

    public static long[] readUniqueLongArray(String fileName) {
        final ArrayList arr = new ArrayList();
        /*final Reference<Long> val;
        final HashSet<Long> used = new HashSet<Long>();
        new File(fileName).invokeMethod("eachLine", new Object[]{new Closure(this, this) {
            public Boolean doCall(Object it) {
                val.set(Long.parseLong((String) it));
                if (!used.contains(val.get()).asBoolean()) {
                    arr << Long.parseLong((String) it);
                    return used.add(val.get());
                }

            }

            public Boolean doCall() {
                return doCall(null);
            }

        }});*/
        return listToLongArray(arr);
    }

    public static int[] listToIntArray(ArrayList inputList) {
        int[] intArr = new int[inputList.size()];
        /*for (int i = 0; i.compareTo(inputList.size) < 0; i++) {
            intArr[i] = ((Integer) (inputList.invokeMethod("get", new Object[]{i})));
        }*/

        return intArr;
    }

    public static long[] listToLongArray(List inputList) {
        long[] longArr = new long[inputList.size()];
        /*for (int i = 0; i.compareTo(inputList.size) < 0; i++) {
            longArr[i] = ((Long) (inputList.invokeMethod("get", new Object[]{i})));
        }*/

        return longArr;
    }

    public static int getIthElement(int i, int[] values) {
        Arrays.sort(values);
        return ((int) (values[i - 1]));
    }

    public static void printArray(Integer[] array) {
       /* array.invokeMethod("each", new Object[]{new Closure(this, this) {
            public Object doCall(Object it) {
                return invokeMethod("print", new Object[]{it + ", "});
            }

            public Object doCall() {
                return doCall(null);
            }

        }});*/
    }

    public static GraphAdjacencyLists readGraphAdjList(String fileName) {
        final GraphAdjacencyLists graph = new GraphAdjacencyLists();
        /*graph.setEdges(new HashMap<Integer, List<Integer>>());
        final Reference<List<Integer>> edges = new groovy.lang.Reference<List<Integer>>(null);
        new File(fileName).invokeMethod("eachLine", new Object[]{new Closure(this, this) {
            public Object doCall(Object line) {
                Integer[] lineNumbers = AlgoUtils.transformLineWithSpaces((String) line);
                Integer baseVertex = lineNumbers[0];
                edges.set(new ArrayList<Integer>(lineNumbers.length - 1));
                graph.getEdges().put(baseVertex, edges.get());
                return lineNumbers.invokeMethod("eachWithIndex", new Object[]{new Closure(DUMMY__1234567890_DUMMYYYYYY___.this, DUMMY__1234567890_DUMMYYYYYY___.this) {
                    public Boolean doCall(Object lnItem, Object index) {
                        if (index > 0) {
                            return edges.get().add((Integer) lnItem);
                        }

                    }

                }});

            }

        }});*/
        return graph;
    }

    public static GraphAdjacencyLists readGraphLineByEdge(String fileName) {
        final GraphAdjacencyLists graph = new GraphAdjacencyLists();
        /*graph.setEdges(new HashMap<Integer, List<Integer>>());
        new File(fileName).invokeMethod("eachLine", new Object[]{new Closure(this, this) {
            public List<Integer> doCall(Object line) {
                Integer[] lineNumbers = AlgoUtils.transformLineWithSpaces((String) line);
                Integer baseVertex = lineNumbers[0];
                if (!graph.getEdges().containsKey(baseVertex).asBoolean()) {
                    graph.getEdges().put(baseVertex, new ArrayList<Integer>());
                }

                graph.getEdges().get(baseVertex).add(lineNumbers[1]);
                if (!graph.getEdges().containsKey(lineNumbers[1]).asBoolean()) {
                    return graph.getEdges().put(lineNumbers[1], new ArrayList<Integer>());
                }

            }

        }});*/
        return graph;
    }

    public static GraphSCC readGraphForSCC(String fileName) {
        final GraphSCC graph = new GraphSCC();
        /*graph.setEdges(new HashMap<Integer, List<Integer>>());
        graph.getReversedGraph().setEdges(new HashMap<Integer, List<Integer>>());
        final Reference<Integer> baseVertex;
        final Reference<Integer> edgeVertex;
        new File(fileName).invokeMethod("eachLine", new Object[]{new Closure(this, this) {
            public List<Integer> doCall(Object line) {
                Integer[] lineNumbers = AlgoUtils.transformLineWithSpaces((String) line);
                baseVertex.set(lineNumbers[0]);
                edgeVertex.set(lineNumbers[1]);
                //file base graph
                if (!graph.getEdges().containsKey(baseVertex.get()).asBoolean()) {
                    graph.getEdges().put(baseVertex.get(), new ArrayList<Integer>());
                }

                graph.getEdges().get(baseVertex.get()).add(lineNumbers[1]);
                if (!graph.getEdges().containsKey(lineNumbers[1]).asBoolean()) {
                    graph.getEdges().put(lineNumbers[1], new ArrayList<Integer>());
                }


                //fill reversed graph
                if (!graph.getReversedGraph().getEdges().containsKey(edgeVertex.get()).asBoolean()) {
                    graph.getReversedGraph().getEdges().put(edgeVertex.get(), new ArrayList<Integer>());
                }

                graph.getReversedGraph().getEdges().get(edgeVertex.get()).add(baseVertex.get());
                if (!graph.getReversedGraph().getEdges().containsKey(baseVertex.get()).asBoolean()) {
                    return graph.getReversedGraph().getEdges().put(baseVertex.get(), new ArrayList<Integer>());
                }

            }

        }});
        graph.setCountVertices(graph.getEdges().size());
        graph.getReversedGraph().setCountVertices(graph.getReversedGraph().getEdges().size());*/
        return graph;
    }

    public static Integer[] transformLineWithSpaces(String spacedLine) {
        /*return ((Integer[]) (spacedLine.split("\\s+").invokeMethod("collect", new Object[]{new Closure(this, this) {
            public Object doCall(Object it) {
                return it.invokeMethod("toInteger", new Object[0]);
            }

            public Object doCall() {
                return doCall(null);
            }

        }})));*/
        return null;
    }

    public static GraphPath readGraphPath(String fileName) {
        final GraphPath graph = new GraphPath();
        /*final Set<Integer> vertices = new HashSet<Integer>();
        final Reference<String[]> items;
        final Reference<String[]> heads;
        final Reference<Integer> currentTail;
        new File(fileName).invokeMethod("eachLine", new Object[]{new Closure(this, this) {
            public Object doCall(Object line) {
                items.set(line.invokeMethod("split", new Object[]{"\\s+"}));
                currentTail.set(Integer.parseInt(items.get()[0]));
                vertices.add(currentTail.get());
                return items.get().invokeMethod("eachWithIndex", new Object[]{new Closure(DUMMY__1234567890_DUMMYYYYYY___.this, DUMMY__1234567890_DUMMYYYYYY___.this) {
                    public Boolean doCall(Object edgeWithLength, Object index) {
                        if (index != 0) {
                            heads.set(edgeWithLength.invokeMethod("split", new Object[]{","}));
                            graph.addEdge(currentTail.get(), Integer.parseInt(heads.get()[0]), Integer.parseInt(heads.get()[1]));
                            return vertices.add(Integer.parseInt(heads.get()[0]));
                        }

                    }

                }});
            }

        }});
        graph.setVerticesCount(((HashSet<Integer>) vertices).size());*/
        return graph;
    }

    public static int sumListElements(List<Integer> list) {
        int res = 0;
        for (int item : list) {
            res += item;
        }

        return res;
    }

    public static List<JobSubstract> readSubstractionJobs(String fileName) {
        final List<JobSubstract> jobs = new ArrayList<JobSubstract>();
        /*final Reference<Boolean> first = new groovy.lang.Reference<boolean>(true);
        final Reference<String[]> temp;
        final Reference<Integer> count = new groovy.lang.Reference<int>(1);
        new File(fileName).invokeMethod("eachLine", new Object[]{new Closure(this, this) {
            public Object doCall(Object line) {
                if (!first.get().asBoolean()) {
                    temp.set(line.invokeMethod("split", new Object[]{" "}));
                    return jobs.add(new JobSubstract(count++, Integer.parseInt(temp.get()[0]), Integer.parseInt(temp.get()[1])));
                } else {
                    return setGroovyRef(first, false);
                }

            }

        }});*/
        return jobs;
    }

    public static List<JobMultiplication> readMultiplicationJobs(String fileName) {
        final List<JobMultiplication> jobs = new ArrayList<JobMultiplication>();
        /*final Reference<Boolean> first = new groovy.lang.Reference<boolean>(true);
        final Reference<String[]> temp;
        final Reference<Integer> count = new groovy.lang.Reference<int>(1);
        new File(fileName).invokeMethod("eachLine", new Object[]{new Closure(this, this) {
            public Object doCall(Object line) {
                if (!first.get().asBoolean()) {
                    temp.set(line.invokeMethod("split", new Object[]{" "}));
                    return jobs.add(new JobMultiplication(count++, Integer.parseInt(temp.get()[0]), Integer.parseInt(temp.get()[1])));
                } else {
                    return setGroovyRef(first, false);
                }

            }

        }});*/
        return jobs;
    }

    public static GraphMST fillGraphForMST(String fileName) {
        final GraphMST graph = new GraphMST();
        /*final Reference<Boolean> first = new groovy.lang.Reference<boolean>(true);
        final Reference<String[]> temp;
        final Reference<EdgeMST> edgeTemp;
        new File(fileName).invokeMethod("eachLine", new Object[]{new Closure(this, this) {
            public Object doCall(Object line) {
                if (!first.get().asBoolean()) {
                    temp.set(line.invokeMethod("split", new Object[]{" "}));
                    edgeTemp.set(new EdgeMST(Integer.parseInt(temp.get()[0]), Integer.parseInt(temp.get()[1]), Integer.parseInt(temp.get()[2])));
                    return graph.getEdgesOrdered().add(edgeTemp.get());
                } else {
                    temp.set(line.invokeMethod("split", new Object[]{" "}));
                    graph.setVerticesCount(Integer.parseInt(temp.get()[0]));
                    return setGroovyRef(first, false);
                }

            }

        }});*/
        return graph;
    }
}
