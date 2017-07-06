# Toma resultado de results/ y lo formatea como un diccionario de python para generar graficos

resultFiles = ["C:/Users/vicen/Documents/GitHub/tarea_3_algoritmos/results/aleatory_graphs_results_tosh.txt",
               ]


# Toma un nombre de archivo que tiene los resultados y lo formatea en una tabla.
def parseToTable(fileName):
    with open(fileName) as textFile:
        lines = [line.rstrip('\n') for line in textFile]
        table = [['edge_prob', 'n',
                  'biggest_deg_result', 'biggest_deg_time [ms]',
                  '2_aprox_result', '2_aprox_time [ms]',
                  '2_aprox_improved_result', '2_aprox_improved_time [ms]']]

        for line in lines:
            if line.split(": ")[0] is 'p':
                edge_prob = line.split(": ")[1]
            elif line.split(": ")[0] is 'n':
                vertex_number = line.split(": ")[1]
            elif line.split(": ")[0] == 'mgrade':
                row = []
                row.append(edge_prob)
                row.append(vertex_number)
                results = line.split("; ")
                for result in results: # result has form <algname>: <result> in <time> [ms]
                    if result == '':
                        continue
                    raw_res = result.split(": ")[1] # <result> in <time> [ms]
                    res = raw_res.split(" in ")[0]
                    time = raw_res.split(" in ")[1].split(" ")[0]
                    row.append(res)
                    row.append(time)
                table.append(row)
        return table


print(parseToTable(resultFiles[0]))


