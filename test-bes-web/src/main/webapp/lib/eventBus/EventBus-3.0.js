//console.info("加载 EventBus-3.0.js");
(function (window) {
    function Map() {
        this.elements = new Array();

        this.size = function () {
            return this.elements.length;
        }

        this.isEmpty = function () {
            return (this.elements.length < 1);
        }

        this.clear = function () {
            this.elements = new Array();
        }

        this.put = function (_key, _value) {
            this.elements.push({
                key: _key,
                value: _value
            });
        }

        this.remove = function (_key) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        this.elements.splice(i, 1);
                        return true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        }

        this.get = function (_key) {
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        return this.elements[i].value;
                    }
                }
            } catch (e) {
                return null;
            }
        }

        this.element = function (_index) {
            if (_index < 0 || _index >= this.elements.length) {
                return null;
            }
            return this.elements[_index];
        }

        this.containsKey = function (_key) {
            varbln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].key == _key) {
                        bln = true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        }

        this.containsValue = function (_value) {
            var bln = false;
            try {
                for (i = 0; i < this.elements.length; i++) {
                    if (this.elements[i].value == _value) {
                        bln = true;
                    }
                }
            } catch (e) {
                bln = false;
            }
            return bln;
        }

        this.values = function () {
            var arr = new Array();
            for (i = 0; i < this.elements.length; i++) {
                arr.push(this.elements[i].value);
            }
            return arr;
        }

        this.keys = function () {
            var arr = new Array();
            for (i = 0; i < this.elements.length; i++) {
                arr.push(this.elements[i].key);
            }
            return arr;
        }
    }


    /**
     * 事件总线实现类.
     * 1.支持跨iframe的订阅发布模式的消息广播;
     * 2.支持跨iframe的点对点消息单向或者双向通信；
     * 传递的事件分为TopicEvent和P2pEvent两种类型。
     * TopicEvent结构：
     * {
	 *   "topic":"", //消息主题(必须)
	 *   "eventCode":"menuChange", //消息编码(必须)
	 *   "eventId":"", //消息唯一ID(必须)
	 *   "data":{}//数据(可选)
	 * }
     *
     * P2pEvent结构：
     * {
	 *   "eventCode":"menuChange", //消息编码(必须)
	 *   "eventId":"", //消息唯一ID(必须)
	 *   "srcEventId":"", //源消息ID(可选)
	 *   "data":{}//数据(可选)
	 * }
     *
     * 消息实际传递过程中，事件通过信封封装。
     * Envelope结构：
     * {
	 *    "type": "", //投递类型 topic|p2p
	 *    "event": {} //事件对象 TopicEvent|P2pEvent
	 * }
     */
    function EventBus() {
        //通信类型
        var TYPE_TOPIC = "topic";//广播
        var TYPE_P2P = "p2p";//点对点

        //订阅关系
        //var subcribers = new Map();
        this.subcribers = new Map()//key====value

        //点对点通信接收处理函数.
        //var p2pReceiver;

        this.registerReceiver = function (receiver) {
            this.p2pReceiver = receiver;
        };
        //添加可信任的ip(白名单)；
        var arr2;
        this.addWhiteList = function (arr) {
            arr2 = arr;
        }

        //事件分发器
        function dispatch(e) {
            var envelope = e.data;
            var type = envelope.type;
            var eventData = envelope.event;
            if (type == TYPE_TOPIC) {
                var topic = eventData.topic;

                var topicSubs = window.EventBus.subcribers.get(topic);

                if (topicSubs) {
                    for (var subcribeRecord in topicSubs) {
                        var callback = topicSubs[subcribeRecord].callback;
                        callback.call(window, eventData);
                    }
                }

            } else {
                var isnull = false;
                if (arr2 == null) {
                    isnull = true;
                }
                if (isnull || arr2.indexOf(e.origin) > -1) {
                    if (window.EventBus.p2pReceiver) {
                        window.EventBus.p2pReceiver.call(window, eventData);
                    }
                } else {
                    console.log("现在arr2里边没有此ip")
                }

            }

        }

        window.addEventListener("message", dispatch);

        /**
         * 发布事件
         * @param  {[TopicEvent]} event [事件对象]
         * @return {[void]}
         */
        this.post = function (event) {
            var envelope = {};
            envelope.type = TYPE_TOPIC;
            envelope.event = event;
            //投递事件到当前window.
            window.postMessage(envelope, '*');

            var iframes = document.getElementsByTagName('iframe');
            if (iframes) {
                for (var i = 0; i < iframes.length; i++) {
                    iframes[i].contentWindow.postMessage(envelope, "*");

                }
            }


        };


        /**
         * 订阅事件主题
         * 释义(把订阅者A添加到主题A)
         *        topic 是主题
         *        handler 是回调函数
         *        subcribeRecord 是订阅者A
         *        topicSubs 是订阅者数组
         *
         * @param  {[type]} topic   [description]
         * @param  {[type]} handler [description]
         * @return {[type]}         [description]
         */
        this.subcribe = function (topic, handler) {
            var topicSubs = this.subcribers.get(topic);

            if (!topicSubs) {
                topicSubs = new Array();
                this.subcribers.put(topic, topicSubs)
            }

            var subcribeRecord = {};
            subcribeRecord.callback = handler;
            topicSubs.push(subcribeRecord);
        };


        /**
         * 取消订阅
         * @param  {[type]} topic   [description]
         * @param  {[type]} handler [description]
         * @return {[type]}         [description]
         */
        this.unSubcribe = function (topic, handler) {
            var topicSubs = this.subcribers.get(topic);

            if (!topicSubs) return;

            for (var i = 0; i < topicSubs.length; i++) {
                var subcribeRecord = topicSubs[i];
                var callback = subcribeRecord.callback;
                if (handler === callback) {
                    topicSubs.splice(i, 1);
                }
            }
        };

        /**
         * 发送事件（点对点通信）
         * @param  {[window]}   targetWin [description]
         * @param  {[P2pEvent]}   event     [description]
         * @return {void}             [description]
         */
        this.send = function (targetWin, event) {
            var envelope = {};
            envelope.type = TYPE_P2P;
            envelope.event = event;
            if (targetWin && targetWin.postMessage) {
            }
            targetWin.postMessage(envelope, '*');
        }
    }


    /**
     * 判断当前是中央事件总线还是本地事件总线
     * @return {Boolean} [true: 中央事件总线 false:本地事件总线]
     */
    this._isCenterBus = function () {
        if (window.top !== window.self) {
            return false;
        }
        return true;
    };

    window['EventBus'] = new EventBus();

})(window);



