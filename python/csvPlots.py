import threading
import pandas as pd
import time
import matplotlib.pyplot as plt

# read the csv
data_set = pd.read_csv('shows.csv')
results = {}

# create a figure with 5 subplots
fig, axs = plt.subplots(3, 2, figsize=(15, 20))


# count for each categoryDescription
def plot_category_description():
    data_set['categoryDescription'].value_counts().sort_values().plot(kind='bar', ax=axs[0, 0])
    axs[0, 0].set_title('Count for each categoryDescription')
    # add results to a json format
    results['categoryDescription'] = data_set['categoryDescription'].value_counts().to_json()


# count for each IPgeo
def plot_ip_geo():
    data_set['IPgeo'].value_counts().sort_values().plot(kind='bar', ax=axs[0, 1])
    axs[0, 1].set_title('Count for each IPgeo')
    # add results to a json format
    results['IPgeo'] = data_set['IPgeo'].value_counts().to_json()


# average of sourcePort for each categoryDescription
def plot_source_port():
    data_set.groupby('categoryDescription')['sourcePort'].mean().sort_values().plot(kind='bar', ax=axs[1, 0])
    axs[1, 0].set_title('Average of sourcePort for each categoryDescription')
    # add results to a json format
    results['categoryDescription/sourcePort'] = data_set.groupby('categoryDescription')['sourcePort'].mean().to_json()


# number of unique Event_DateTime for each eventDescription
def plot_event_date_time():
    data_set.groupby('eventDescription')['Event_DateTime'].nunique().sort_values().plot(kind='bar', ax=axs[1, 1])
    axs[1, 1].set_title('Number of unique Event_DateTime for each eventDescription')
    # add results to a json format
    results['eventDescription'] = data_set.groupby('eventDescription')['Event_DateTime'].nunique().to_json()


# count sourcePort for each protocolName
def plot_source_port_protocol_name():
    data_set.groupby('protocolName')['sourcePort'].count().sort_values().plot(kind='pie', ax=axs[2, 0])
    axs[2, 0].set_title('Count sourcePort for each protocolName')
    # add results to a json format
    results['protocolName'] = data_set.groupby('protocolName')['sourcePort'].count().to_json()


# count destinationPort for each protocolName
def plot_destination_port():
    data_set.groupby('protocolName')['destinationPort'].count().sort_values().plot(kind='pie', ax=axs[2, 1])
    axs[2, 1].set_title('Count destinationPort for each protocolName')
    # add results to a json format
    results['protocolName/destinationPort'] = data_set.groupby('protocolName')['destinationPort'].count().to_json()


def thread_function(thread_id):
    plot_functions = {
        0: plot_category_description,
        1: plot_ip_geo,
        2: plot_source_port,
        3: plot_event_date_time,
        4: plot_source_port_protocol_name,
        5: plot_destination_port
    }
    plot_func = plot_functions.get(thread_id)
    if plot_func:
        plot_func()


# thread creation
def main():
    num_of_threads = 6
    threads = []

    start_time = time.time()

    for i in range(num_of_threads):
        threads.append(
            threading.Thread(target=thread_function, args=(i,))
        )

    for thread in threads:
        thread.start()

    for thread in threads:
        thread.join()

    # print results
    print(results)
    for i in results:
        print(i, results[i])

    print("--- %s seconds ---" % (time.time() - start_time))

    # adjust spacing
    plt.subplots_adjust(hspace=1)
    plt.show()


if __name__ == '__main__':
    main()
